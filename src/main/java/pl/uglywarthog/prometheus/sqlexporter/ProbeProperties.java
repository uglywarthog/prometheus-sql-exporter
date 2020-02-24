package pl.uglywarthog.prometheus.sqlexporter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties("sql-exporter")
public class ProbeProperties {

    private List<Probe> probes;

}
