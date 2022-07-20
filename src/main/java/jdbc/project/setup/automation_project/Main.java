package jdbc.project.setup.automation_project;

import helpers.PropertiesHelper;

import java.sql.*;

public class Main {
    private static final PropertiesHelper  PROPERTIES_HELPER = new PropertiesHelper();
    private static final String DB_URL = PROPERTIES_HELPER.getDbUrl();
    private static final String DB_USER = PROPERTIES_HELPER.getDbUsername();
    private static final String DB_PASS = PROPERTIES_HELPER.getDbPassword();
    private static final String QUERY = "SELECT customer_id, age, name, phone FROM customers";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("ID: " + rs.getInt("customer_id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", Name: " + rs.getString("name"));
                System.out.println(", phone: " + rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}