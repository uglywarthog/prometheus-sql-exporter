package pl.uglywarthog.prometheus.sqlexporter;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqProbe implements MeterBinder {

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        DbUtil dbUtil = new DbUtil("jdbc:h2:mem:testdb", "sa", "");
        JdbcQuery query1 = new JdbcQuery("select data from test where name = 'test1'", dbUtil);
        JdbcQuery query2 = new JdbcQuery("select data from test where name = 'test2'", dbUtil);

        Gauge.builder("test", query1::get)
                .description("test description")
                .baseUnit("test_base_unit")
                .tag("tag", "test-tag")
                .register(meterRegistry);

        Gauge.builder("test", query2::get)
                .description("test description 2")
                .baseUnit("test_base_unit_2")
                .tag("tag2", "test-tag2")
                .register(meterRegistry);
    }
}