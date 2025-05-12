SUMMARY = "Adafruit Platform Detect - pure Python module"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/LICENSE;md5=59d38d9aef0f1ce6e125ff8c2831a249"

SRC_URI = "git://github.com/adafruit/Adafruit_Python_PlatformDetect.git;protocol=https;branch=main"
SRCREV = "c9518b0a55f69949a3523d2a97520cd0a73375d6"

S = "${WORKDIR}/git"

inherit python3-dir

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/adafruit_platformdetect
    cp -r ${S}/adafruit_platformdetect/* ${D}${PYTHON_SITEPACKAGES_DIR}/adafruit_platformdetect/
}

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}/adafruit_platformdetect"
RDEPENDS:${PN} += "python3-core"
