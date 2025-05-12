SUMMARY = "Adafruit Sensor Stack (Blinka)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/LICENSE;md5=59d38d9aef0f1ce6e125ff8c2831a249"

SRC_URI = "file://Adafruit_Blinka"

S = "${WORKDIR}/Adafruit_Blinka"

inherit setuptools3

DEPENDS += "python3-setuptools-scm-native"
do_install:append() {
    # Remove prebuilt binaries
    find ${D}${libdir}/python3.12/site-packages/adafruit_blinka/ -name "libgpiod_pulsein*" -exec rm -f {} +  
}

