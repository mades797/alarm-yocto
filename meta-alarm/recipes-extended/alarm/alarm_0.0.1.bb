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
"

SRCREV = "c7934ce9bea03daeb1253a672e9bbb7a4f006093"
# S = "${UNPACKDIR}"
FILES:${PN} += "${systemd_unitdir}/system/alarm.service"
RDEPENDS:${PN} += "python3-core"
BBCLASSEXTEND = "native"

inherit systemd

SYSTEMD_SERVICE:${PN} = "alarm.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append () {
    install -d ${D}${systemd_unitdir}
    install -Dm 0644 ${WORKDIR}/alarm.service ${D}${systemd_unitdir}/system/alarm.service
    install -Dm 0755 ${WORKDIR}/git/main.py ${D}${USRBINPATH}/alarm
}
