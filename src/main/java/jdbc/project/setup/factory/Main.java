package jdbc.project.setup.factory;

import helpers.conections.helpers.DBFactoryConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private static final String QUERY = "SELECT customer_id, age, name, phone FROM customers";

    public static void main(String[] args) {

        try (DBFactoryConnection db_conn = new DBFactoryConnection();
             Connection conn = db_conn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {

            while (rs.next()) {
                // Retrieve by column name
                System.out.print("ID: " + rs.getInt("customer_id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", Name: " + rs.getString("name"));
                System.out.println(", phone: " + rs.getString("phone"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}