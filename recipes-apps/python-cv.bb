DESCRIPTION = "FOR CONVERTING PY FILE"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://testmqtt.py file://embedded_pr_fi.py file://COPYING.MIT"

S = "${WORKDIR}"

do_install(){
       install -d ${D}${bindir}
       install -m 0755 ${S}/testmqtt.py ${D}${bindir}/testmqtt
       install -m 0755 ${S}/embedded_pr_fi.py ${D}${bindir}/final_proj_gr2
}

RDEPENDS:${PN} += "python3"
