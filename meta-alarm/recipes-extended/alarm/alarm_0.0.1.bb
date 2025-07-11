SUMMARY = "Alarm package"
# nooelint: oelint.vars.descriptiontoobrief - fixme
DESCRIPTION = "TODO"
# nooelint: oelint.vars.homepageprefix,oelint.vars.homepageping - fixme
HOMEPAGE = "TODO"
# nooelint: oelint.vars.bugtrackerisurl - fixme
BUGTRACKER = "TODO"
SECTION = "extended"
LICENSE = "GPL-3.0-only"
CVE_PRODUCT = "${BPN}"

# nooelint: oelint.var.licenseremotefile - fixme
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"
SRC_URI = "\
    git://github.com/mades797/alarm.git;protocol=https;branch=main \
    file://alarm.service \
    file://alarm-controller.service \
"

SRCREV = "a704e00193e205458c18e40090c31e0a5e1ed25a"
# S = "${UNPACKDIR}"
FILES:${PN} += "${systemd_unitdir}/system/alarm.service"
FILES:${PN} += "${systemd_unitdir}/system/alarm-controller.service"
FILES:${PN} += "/opt/alarm/common.py"
RDEPENDS:${PN} += "python3-core"
BBCLASSEXTEND = "native"

inherit systemd

SYSTEMD_SERVICE:${PN} = "alarm-controller.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append () {
    install -d ${D}${systemd_unitdir}
    install -d ${D}/opt/${PN}
    install -Dm 0644 ${WORKDIR}/alarm.service ${D}${systemd_unitdir}/system/alarm.service
    install -Dm 0644 ${WORKDIR}/alarm-controller.service ${D}${systemd_unitdir}/system/alarm-controller.service
    install -Dm 0755 ${WORKDIR}/git/main.py ${D}${USRBINPATH}/alarm
    install -Dm 0755 ${WORKDIR}/git/controller.py ${D}${USRBINPATH}/alarm-controller
    install -Dm 0644 ${WORKDIR}/git/common.py ${D}/opt/${PN}/common.py
}
