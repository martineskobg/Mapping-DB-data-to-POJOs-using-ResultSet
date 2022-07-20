package helpers.queries;

public interface Queries {
    String insertQuery = "INSERT INTO customers (name, email, phone, age, address_id, gdpr," +
            " is_profile_active, profile_created, profile_deactivated, deactivation_reason, notes)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    String updateQuery = "UPDATE customers SET name = 'Bicheto' WHERE customer_id = ?;";
    String deleteRecordQuery = "DELETE FROM customers WHERE customer_id = ?";
    String deleteAllQuery = "DELETE FROM customers";
    String getLastIdQuery = "SELECT customer_id FROM customers ORDER BY customer_id DESC LIMIT 1";

    String checkIfIdExistsQuery = "SELECT COUNT(customer_id) FROM customers WHERE customer_id = ?";
    String getRecordsCountQuery = "SELECT COUNT(customer_id) FROM customers;";

    String selectCustomerQuery  = "SELECT * FROM customers WHERE customer_id = ?";

}
