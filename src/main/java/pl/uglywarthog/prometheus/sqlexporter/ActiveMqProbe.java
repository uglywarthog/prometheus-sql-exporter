package pl.uglywarthog.prometheus.sqlexporter;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqProbe implements MeterBinder {

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        Gauge.builder("test", () -> 5)
                .description("test description")
                .baseUnit("test_base_unit")
                .tag("tag", "test-tag")
                .register(meterRegistry);
    }
}