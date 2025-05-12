SUMMARY = "Adafruit Platform Detect - pure Python module"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/LICENSE;md5=59d38d9aef0f1ce6e125ff8c2831a249"

SRC_URI = "git://github.com/viking-art/change_i2c.git;protocol=https;branch=main"
SRCREV = "128a10a1c961770ee0ca8f434eb8ebd97ddd7b55"

S = "${WORKDIR}/git"

inherit python3-dir

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/adafruit_bus_device
    cp -r ${S}/adafruit_bus_device/* ${D}${PYTHON_SITEPACKAGES_DIR}/adafruit_bus_device/
}

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/adafruit_bus_device"
RDEPENDS:${PN} += "python3-core"
