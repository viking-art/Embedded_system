DESCRIPTION = "WiFi configuration files for NetworkManager"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI += " file://COPYING.MIT \
    file://wifime.nmconnection \
    file://wifime2.nmconnection \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/NetworkManager/system-connections/
    install -m 0600 ${WORKDIR}/wifime.nmconnection ${D}${sysconfdir}/NetworkManager/system-connections/
    install -m 0600 ${WORKDIR}/wifime2.nmconnection ${D}${sysconfdir}/NetworkManager/system-connections/
}
