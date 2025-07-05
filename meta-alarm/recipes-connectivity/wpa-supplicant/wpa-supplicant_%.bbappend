FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://wpa_supplicant.conf \
            file://wpa_supplicant.service"

do_install:append () {
    install -m 644 ${WORKDIR}/wpa_supplicant.conf ${D}${sysconfdir}/wpa_supplicant.conf
    install -m 644 ${WORKDIR}/wpa_supplicant.service ${D}${systemd_system_unitdir}/wpa_supplicant.service
}

SYSTEMD_SERVICE:${PN} = "wpa_supplicant.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
