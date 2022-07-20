package helpers.conections.helpers;

import helpers.PropertiesHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSingletonConnection {
    //create an object of DBSingletonConnection
    private static DBSingletonConnection instance = new DBSingletonConnection();
    private final Connection connection;

    //make the constructor private so that this class cannot be
    //instantiated
    private DBSingletonConnection() {

        try {
            PropertiesHelper PROPERTIES_HELPER = new PropertiesHelper();
            this.connection = DriverManager.getConnection(
                    PROPERTIES_HELPER.getDbUrl(),
                    PROPERTIES_HELPER.getDbUsername(),
                    PROPERTIES_HELPER.getDbPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Get connection
    public Connection getConnection() {
        return this.connection;
    }

    public static DBSingletonConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBSingletonConnection();
        }
        return instance;
    }
}
