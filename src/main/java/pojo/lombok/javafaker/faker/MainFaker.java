package pojo.lombok.javafaker.faker;

import helpers.customer.helper.CustomerFakerHelper;
import helpers.conections.helpers.DBFactoryConnection;
import pojo.lombok.javafaker.customer.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainFaker {
    private static ResultSet rs = null;
    private static final String SELECT_QUERY = "SELECT customer_id, age, name, phone FROM customers";
    private static String insertQuery = "INSERT INTO customers (name, email, phone, age, address_id, gdpr," +
            " is_profile_active, profile_created, profile_deactivated, deactivation_reason, notes)";


    public static void main(String[] args) throws SQLException {

        Customer customer = new CustomerFakerHelper().createCustomer();

        String values = String.format("VALUES ('%s', '%s', '%s', %d, %d, %b, %b, '%s', '%s', '%s', '%s')",
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAge(),
                customer.getAddressId(),
                customer.isGdpr(),
                customer.isProfileActive(),
                customer.getProfileCreated(),
                customer.getProfileDeactivated(),
                customer.getDeactivationReason(),
                customer.getNote());

        insertQuery += values;

        try (DBFactoryConnection db_conn = new DBFactoryConnection();
             Connection conn = db_conn.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(insertQuery);
            rs = stmt.executeQuery(SELECT_QUERY);

            while (rs.next()) {
                // Retrieve by column name
                System.out.print("ID: " + rs.getInt("customer_id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", Name: " + rs.getString("name"));
                System.out.println(", phone: " + rs.getString("phone"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            rs.close();
        }
    }
}
