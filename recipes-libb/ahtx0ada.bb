SUMMARY = "Adafruit Sensor Stack (Blinka, BMP280, AHTx0)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/LICENSE;md5=59d38d9aef0f1ce6e125ff8c2831a249"

SRC_URI = "git://github.com/adafruit/Adafruit_CircuitPython_AHTx0.git;branch=main;protocol=https"

SRCREV  = "6aafa832bb7c774f740634d5c22c0a27a04123e2"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${libdir}/python3.12/site-packages/adafruit_ahtx0
    cp -r ${S}/* ${D}${libdir}/python3.12/site-packages/adafruit_ahtx0/
}
FILES:${PN} += "${libdir}/python3.12/site-packages"
