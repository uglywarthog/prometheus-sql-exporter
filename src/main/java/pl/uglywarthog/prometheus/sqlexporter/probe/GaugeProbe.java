package pl.uglywarthog.prometheus.sqlexporter.probe;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.uglywarthog.prometheus.sqlexporter.properties.Probe;
import pl.uglywarthog.prometheus.sqlexporter.properties.ProbeProperties;
import pl.uglywarthog.prometheus.sqlexporter.query.JdbcQuery;

import java.util.logging.Level;

@Component
@RequiredArgsConstructor
@Log
public class GaugeProbe implements MeterBinder {

    private final ProbeProperties probes;

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        probes.getProbes().forEach(probe -> setupProbe(probe, meterRegistry));
    }

    private void setupProbe(Probe probe, MeterRegistry meterRegistry) {
        log.log(Level.INFO, "Setting up probe {0}", probe.getName());

        JdbcQuery query = new JdbcQuery(probe.getUrl(), probe.getUser(), probe.getPassword(), probe.getQuery());
        Gauge.builder(probe.getName(), query::getAsDouble).register(meterRegistry);
    }
}