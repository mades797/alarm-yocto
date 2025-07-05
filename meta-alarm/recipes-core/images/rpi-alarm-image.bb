SUMMARY = "TODO"
DESCRIPTION = "Image for RPI alarm"
# nooelint: oelint.vars.bugtrackerisurl TODO
BUGTRACKER = "TODO"
SECTION = "core"
LICENSE = "GPL-3.0-only"

CVE_PRODUCT = "${BPN}"

inherit core-image

COMPATIBLE_MACHINE = "raspberrypi3-64"
IMAGE_INSTALL:append = " alarm"
IMAGE_INSTALL:append = " systemd"
IMAGE_INSTALL:append = " bluez5"
IMAGE_INSTALL:append = " i2c-tools"
IMAGE_INSTALL:append = " python3-smbus"
IMAGE_INSTALL:append = " bridge-utils"
IMAGE_INSTALL:append = " iptables"
IMAGE_INSTALL:append = " grep"
IMAGE_INSTALL:append = " cpufrequtils"
IMAGE_INSTALL:append = " os-release"
IMAGE_INSTALL:append = " procps"
IMAGE_INSTALL:append = " file"
IMAGE_INSTALL:append = " zile"
IMAGE_INSTALL:append = " python3-evdev"
IMAGE_INSTALL:append = " rpi-gpio"
IMAGE_INSTALL:append = " python3-systemd"
IMAGE_INSTALL:append = " packagegroup-rpi-headless-wifi"

AUTH_KEYS_DIR ??= "${THISDIR}/auth-keys"

# Function to install the auth keys
configure_ssh_auth_key() {
    install -d -m 755 ${IMAGE_ROOTFS}/${sysconfdir}/ssh
    AUTH_KEYS=$(ls ${AUTH_KEYS_DIR}/*.pub)
    for auth_key in ${AUTH_KEYS}; do
        key=$(basename "$auth_key" | cut -d '.' -f1)
        install -m 600 ${AUTH_KEYS_DIR}/${key} ${IMAGE_ROOTFS}/${sysconfdir}/ssh/${key}
        install -m 644 ${AUTH_KEYS_DIR}/${key}.pub ${IMAGE_ROOTFS}/${sysconfdir}/ssh/${key}.pub
    done
}
configure_ssh_auth_key[doc] = "Install ssh keys"

# Leading ; shouldn't be required, but seems to fail without it
ROOTFS_POSTPROCESS_COMMAND:append = " ;configure_ssh_auth_key;"
# nooelint: oelint.var.badimagefeature.allow-root-login,oelint.var.badimagefeature.empty-root-password,oelint.var.badimagefeature.allow-empty-password - TODO
EXTRA_IMAGE_FEATURES = "ssh-server-openssh allow-empty-password empty-root-password allow-root-login"
