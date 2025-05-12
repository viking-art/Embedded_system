SUMMARY = "Adafruit Sensor Stack (Blinka, BMP280, AHTx0)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/LICENSE;md5=59d38d9aef0f1ce6e125ff8c2831a249"

SRC_URI = "git://github.com/adafruit/Adafruit_CircuitPython_BMP280.git;branch=main;protocol=https"

SRCREV = "f4bba4fe2326d0bbeb9c121614c7e807076f4c13"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${libdir}/python3.12/site-packages/adafruit_bmp280
    cp -r ${S}/* ${D}${libdir}/python3.12/site-packages/adafruit_bmp280/
}

FILES:${PN} += "${libdir}/python3.12/site-packages"
