#!/usr/bin/env python3

import paho.mqtt.client as mqtt
import time
import json
import random
import logging
from paho.mqtt.client import Client, MQTTv311, CallbackAPIVersion

logging.basicConfig(filename='dht11.log', level=logging.INFO)

MQTT_BROKER = "localhost"
MQTT_PORT = 1883
MQTT_TOPIC = "sensors/dht11"
MQTT_CLIENT_ID = "dht11_simulator"

def on_connect(client, userdata, flags, rc, properties=None):
    logging.info(f"Kết nối MQTT, mã trả về: {rc}")
    if rc == 0:
        print("Đã kết nối với MQTT Broker!")
    else:
        print(f"Kết nối thất bại, mã lỗi {rc}")
        logging.error(f"Kết nối thất bại, mã lỗi {rc}")

#client = mqtt.Client(client_id=MQTT_CLIENT_ID, protocol=mqtt.MQTTv5)
client = Client(
    client_id=MQTT_CLIENT_ID,
    protocol=MQTTv311,
    callback_api_version=CallbackAPIVersion.VERSION1
)
client.on_connect = on_connect

try:
    client.connect(MQTT_BROKER, MQTT_PORT, keepalive=60)
except Exception as e:
    logging.error(f"Lỗi kết nối MQTT: {e}")
    print(f"Lỗi kết nối MQTT: {e}")

def read_dht11():
    try:
        humidity = random.uniform(40.0, 80.0)
        temperature = random.uniform(20.0, 30.0)
        data = {
            "temperature": temperature,
            "humidity": humidity,
            "timestamp": time.time()
        }
        logging.info(f"Dữ liệu mô phỏng: {data}")
        return data
    except Exception as e:
        logging.error(f"Lỗi read_dht11: {e}")
        print(f"Lỗi read_dht11: {e}")
        return None

def publish_data(data):
    try:
        payload = json.dumps(data)
        result = client.publish(MQTT_TOPIC, payload, qos=1)
        if result.rc == mqtt.MQTT_ERR_SUCCESS:
            logging.info(f"Đã xuất bản: {payload}")
            print(f"Đã xuất bản: {payload}")
        else:
            logging.error("Không thể xuất bản dữ liệu")
            print("Không thể xuất bản dữ liệu")
    except Exception as e:
        logging.error(f"Lỗi publish_data: {e}")
        print(f"Lỗi publish_data: {e}")

def main():
    client.loop_start()
    try:
        while True:
            sensor_data = read_dht11()
            if sensor_data:
                publish_data(sensor_data)
            time.sleep(5)
    except KeyboardInterrupt:
        print("Chương trình bị dừng")
        logging.info("Chương trình bị dừng")
    finally:
        client.loop_stop()
        client.disconnect()

if __name__ == "__main__":
    main()
