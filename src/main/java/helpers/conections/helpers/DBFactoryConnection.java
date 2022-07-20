package helpers.conections.helpers;

import helpers.PropertiesHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBFactoryConnection implements AutoCloseable {
    private final PropertiesHelper  PROPERTIES_HELPER = new PropertiesHelper();
    private Connection connection;

    public DBFactoryConnection() throws SQLException {
        createConnection();
    }

    private void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(
                PROPERTIES_HELPER.getDbUrl(),
                PROPERTIES_HELPER.getDbUsername(),
                PROPERTIES_HELPER.getDbPassword());
    }
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void close() {
        System.out.println("Connection closed");
    }
}
