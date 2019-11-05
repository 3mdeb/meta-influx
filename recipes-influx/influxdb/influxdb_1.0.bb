DESCRIPTION = "INFLUXDB 1.7.9"



PR = "r1"

SRC_URI = "https://dl.influxdata.com/influxdb/releases/influxdb-1.7.9_linux_armhf.tar.gz \
          file://LICENSE \
          "
SRC_URI[sha256sum] = "c3f87938f8349649bf51db0a23683c06515548f6a84a9bcf0068a095e539e99e"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

INSANE_SKIP_${PN}_append = "already-stripped"

S = "${WORKDIR}/influxdb-1.7.9-1"

do_install() {
    #${sysconfdir} = /etc
    #${bindir} = /usr/bin
    #${libdir} = /usr/lib
    #${datadir} = /usr/share
    #${localstatedir} = /var

    # /etc
    install -d ${D}${sysconfdir}/influxdb
    install -d ${D}${sysconfdir}/logrotate.d

    install -m 0644 ${S}/etc/influxdb/influxdb.conf ${D}${sysconfdir}/influxdb/
    install -m 0644 ${S}/etc/logrotate.d/influxdb ${D}${sysconfdir}/logrotate.d/

    # /usr/bin
    install -d ${D}${bindir}

    install -m 0755 ${S}/usr/bin/influx ${D}${bindir}/
    install -m 0755 ${S}/usr/bin/influxd ${D}${bindir}/
    install -m 0755 ${S}/usr/bin/influx_inspect ${D}${bindir}/
    install -m 0755 ${S}/usr/bin/influx_stress ${D}${bindir}/
    install -m 0755 ${S}/usr/bin/influx_tsm ${D}${bindir}/

    # /usr/lib
    install -d ${D}${libdir}/influxdb/scripts

    install -m 0644 ${S}/usr/lib/influxdb/scripts/influxdb.service ${D}${libdir}/influxdb/scripts/
    install -m 0644 ${S}/usr/lib/influxdb/scripts/init.sh ${D}${libdir}/influxdb/scripts/

    # /usr/share
    install -d ${D}${datadir}/man/man1

    install -m 0644 ${S}/usr/share/man/man1/influx.1.gz ${D}${datadir}/man/man1/
    install -m 0644 ${S}/usr/share/man/man1/influxd.1.gz ${D}${datadir}/man/man1/
    install -m 0644 ${S}/usr/share/man/man1/influxd-backup.1.gz ${D}${datadir}/man/man1/
    install -m 0644 ${S}/usr/share/man/man1/influxd-config.1.gz ${D}${datadir}/man/man1/
    install -m 0644 ${S}/usr/share/man/man1/influxd-restore.1.gz ${D}${datadir}/man/man1/
    install -m 0644 ${S}/usr/share/man/man1/influxd-run.1.gz ${D}${datadir}/man/man1/
    install -m 0644 ${S}/usr/share/man/man1/influxd-version.1.gz ${D}${datadir}/man/man1/
    install -m 0644 ${S}/usr/share/man/man1/influx_inspect.1.gz ${D}${datadir}/man/man1/
    install -m 0644 ${S}/usr/share/man/man1/influx_stress.1.gz ${D}${datadir}/man/man1/
    install -m 0644 ${S}/usr/share/man/man1/influx_tsm.1.gz ${D}${datadir}/man/man1/

    # /var/lib
    install -d ${D}${localstatedir}/lib/influxdb
    install -d ${D}${localstatedir}/log/influxdb

    rm -rf ${S}/*
}