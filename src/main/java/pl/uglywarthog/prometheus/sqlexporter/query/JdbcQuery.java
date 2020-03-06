package pl.uglywarthog.prometheus.sqlexporter.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;

@RequiredArgsConstructor
@Log
public class JdbcQuery implements DoubleSupplier {

    private final String url;
    private final String username;
    private final String password;
    private final String query;

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public double getAsDouble() {
        log.log(Level.INFO, "Evaluating probe {0}", query);
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error while executing query {0}; Cause: {1}", new Object[]{query, e.getMessage()});
        }

        // In case of error return null which will be reported as NaN
        throw new RuntimeException("No value");
    }

}
