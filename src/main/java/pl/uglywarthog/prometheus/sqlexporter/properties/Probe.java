package pl.uglywarthog.prometheus.sqlexporter.properties;

import lombok.Data;

@Data
public class Probe {

    private String name;
    private String url;
    private String user;
    private String password;
    private String query;
}
