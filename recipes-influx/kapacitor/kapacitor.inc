DESCRIPTION = "Kapacitor"
SUMMARY = "Kapacitor is a real-time streaming data processing engine."
HOMEPAGE = "https://www.influxdata.com/time-series-platform/kapacitor/"

INSANE_SKIP_${PN}_append = "already-stripped"

FILES_${PN} += "${datadir}/*"

do_install() {
    #${sysconfdir} = /etc
    #${bindir} = /usr/bin
    #${libdir} = /usr/lib
    #${datadir} = /usr/share
    #${localstatedir} = /var

    # /etc
    install -d ${D}${sysconfdir}/kapacitor
    install -d ${D}${sysconfdir}/logrotate.d

    install -m 0644 ${S}/etc/kapacitor/kapacitor.conf ${D}${sysconfdir}/kapacitor/
    install -m 0644 ${S}/etc/logrotate.d/kapacitor ${D}${sysconfdir}/logrotate.d/

    # /usr/bin
    install -d ${D}${bindir}

    install -m 0755 ${S}/usr/bin/kapacitor ${D}${bindir}/
    install -m 0755 ${S}/usr/bin/kapacitord ${D}${bindir}/
    install -m 0755 ${S}/usr/bin/tickfmt ${D}${bindir}/

    # /usr/lib
    install -d ${D}${systemd_unitdir}/system
    sed -i 's/User=kapacitor/User=root/g' ${S}/usr/lib/kapacitor/scripts/kapacitor.service
    sed -i 's/Group=kapacitor/Group=root/g' ${S}/usr/lib/kapacitor/scripts/kapacitor.service
    install -m 0644 ${S}/usr/lib/kapacitor/scripts/kapacitor.service ${D}${systemd_unitdir}/system
    #install -m 0755 ${S}/usr/lib/kapacitor/scripts/init.sh ${D}${libdir}/kapacitor/scripts/
    #install -m 0644 ${S}/usr/lib/kapacitor/scripts/kapacitor.service ${D}${libdir}/kapacitor/scripts/

    # /usr/share
    install -d ${D}${datadir}/bash-completion/completions

    install -m 0644 ${S}/usr/share/bash-completion/completions/kapacitor ${D}${datadir}/bash-completion/completions/

    # /var/lib
    install -d ${D}${localstatedir}/lib/kapacitor
    install -d ${D}${localstatedir}/log/kapacitor

}
inherit systemd
SYSTEMD_SERVICE_${PN} = "kapacitor.service"