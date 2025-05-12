#!/usr/bin/env python3

import paho.mqtt.client as mqtt
import time
import json
import logging
from paho.mqtt.client import Client, MQTTv311, CallbackAPIVersion
import board
from busio import I2C
from adafruit_bmp280 import adafruit_bmp280
from adafruit_ahtx0 import adafruit_ahtx0

logging.basicConfig(filename='sensor.log', level=logging.INFO)

MQTT_BROKER = input("Input IP MQTT Broker: ").strip()
MQTT_PORT = 1883
MQTT_TOPIC = "sensors/monitoring"
MQTT_CLIENT_ID = "monitor"

def on_connect(client, userdata, flags, rc, properties=None):
    logging.info(f"Connect MQTT, code: {rc}")
    if rc == 0:
        print("Connected to MQTT Broker")
    else:
        print(f"Connect failed {rc}")
        logging.error(f"Connect failed, code {rc}")

client = Client(
    client_id=MQTT_CLIENT_ID,
    protocol=MQTTv311,
    callback_api_version=CallbackAPIVersion.VERSION1
)
client.on_connect = on_connect

try:
    client.connect(MQTT_BROKER, MQTT_PORT, keepalive=60)
except Exception as e:
    logging.error(f"Error connecting to MQTT: {e}")
    print(f"Error connecting to MQTT: {e}")

i2c = I2C(board.SCL, board.SDA)
bmp280 = adafruit_bmp280.Adafruit_BMP280_I2C(i2c)
aht20 = adafruit_ahtx0.AHTx0(i2c)

def read_sensor_data():
    try:
        temperature = aht20.temperature
        humidity = aht20.relative_humidity
        pressure = bmp280.pressure

        return {
            "temperature": round(temperature, 2),
            "humidity": round(humidity, 2),
            "pressure": round(pressure, 2)
        }
    except Exception as e:
        logging.error(f"Sensor read error: {e}")
        return None

def publish_data(data):
    try:
        payload = json.dumps(data)
        result = client.publish(MQTT_TOPIC, payload, qos=1)
        if result.rc == mqtt.MQTT_ERR_SUCCESS:
            logging.info(f"Published: {payload}")
            print(f"Published: {payload}")
        else:
            logging.error("Cannot Publish")
            print("Cannot Publish")
    except Exception as e:
        logging.error(f"Error publish_data: {e}")
        print(f"Error publish_data: {e}")

def main():
    client.loop_start()
    try:
        while True:
            sensor_data = read_sensor_data()
            if sensor_data:
                publish_data(sensor_data)
            time.sleep(0.1)
    except KeyboardInterrupt:
        print("Stopped")
        logging.info("Program stopped")
    finally:
        client.loop_stop()
        client.disconnect()

if __name__ == "__main__":
    main()
