SUMMARY = "TODO"
DESCRIPTION = "Raspberry Pi headless package group (wifi)"
# nooelint: oelint.vars.bugtrackerisurl - fixme
BUGTRACKER = "TODO"
SECTION = "core"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

COMPATIBLE_MACHINE = "raspberrypi3-64"

PACKAGES = "\
    packagegroup-rpi-headless-wifi \
"

# IMAGE_INSTALL:append = " dhcpcd"
IMAGE_INSTALL:append = " iw"
IMAGE_INSTALL:append = " wpa-supplicant"

RDEPENDS:${PN} = "\
    dhcpcd \
"
