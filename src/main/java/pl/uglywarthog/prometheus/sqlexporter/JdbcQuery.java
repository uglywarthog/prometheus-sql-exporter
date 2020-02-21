package pl.uglywarthog.prometheus.sqlexporter;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Log
public class JdbcQuery implements Supplier<Double> {

    private final String query;
    private final DbUtil dbUtil;

    @Override
    public Double get() {
        return dbUtil.executeQuery(query);
    }
}
