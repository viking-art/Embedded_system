SUMMARY = "Adafruit Platform Detect - pure Python module"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/LICENSE;md5=59d38d9aef0f1ce6e125ff8c2831a249"

SRC_URI = "git://github.com/adafruit/Adafruit_Python_PureIO.git;protocol=https;branch=main"
SRCREV = "117f1f19f08a2c6edd11ef0281586ba5301dd649"

S = "${WORKDIR}/git"

inherit python3-dir

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/Adafruit_PureIO
    cp -r ${S}/Adafruit_PureIO/* ${D}${PYTHON_SITEPACKAGES_DIR}/Adafruit_PureIO/
}

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/Adafruit_PureIO"
RDEPENDS:${PN} += "python3-core"

