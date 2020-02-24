package pl.uglywarthog.prometheus.sqlexporter;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Component
@RequiredArgsConstructor
@Log
public class ActiveMqProbe implements MeterBinder {

    private final ProbeProperties probes;

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        probes.getProbes().forEach(probe -> setupProbe(probe, meterRegistry));
    }

    private void setupProbe(Probe probe, MeterRegistry meterRegistry) {
        log.log(Level.INFO, "Setting up probe {0}", probe.getName());

        DbUtil dbUtil = new DbUtil(probe.getUrl(), probe.getUser(), probe.getPassword());
        JdbcQuery query = new JdbcQuery(probe.getQuery(), dbUtil);

        Gauge.builder(probe.getName(), query::get)
                .register(meterRegistry);
    }
}