package pl.uglywarthog.prometheus.sqlexporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlExporterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlExporterApplication.class, args);
    }

}
