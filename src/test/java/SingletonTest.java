import helpers.conections.helpers.DBSingletonConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SingletonTest {
    private static final String QUERY = "SELECT name FROM customers";
    private static final String TEST_NAME = "Petyr Petrov";
    private ArrayList<String> customersNames = new ArrayList<>();
    private DBSingletonConnection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;


    @BeforeEach
    public void setupConnection() {
        try {
            connection = DBSingletonConnection.getInstance();
            statement = connection.getConnection().prepareStatement(QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customersNames.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testCustomersNames() {
        boolean isContains = customersNames.contains(TEST_NAME);
        Assertions.assertTrue(isContains, "There is no such customer name: " + TEST_NAME);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.getConnection().close();
        resultSet.close();
        statement.close();
    }


}
