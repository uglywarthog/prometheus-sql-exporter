package pl.uglywarthog.prometheus.sqlexporter;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.logging.Level;

@RequiredArgsConstructor
@Log
public class DbUtil {
    private final String url;
    private final String username;
    private final String password;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public Double executeQuery(String query) {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "Error while executing query {0}", query);
            e.printStackTrace();
        }
        return null;
    }
}
