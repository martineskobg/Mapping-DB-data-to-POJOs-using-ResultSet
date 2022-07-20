package pojos.and.daos;

import helpers.conections.helpers.DBFactoryConnection;
import helpers.customer.helper.CustomerFakerHelper;
import helpers.queries.Queries;
import pojo.lombok.javafaker.customer.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerDaoImpl implements CustomerDao<Customer>, Queries {


    Customer customer;
    private final Connection conn;
    private PreparedStatement preparedStatement;
    private Statement stm;

    public CustomerDaoImpl() {

        this.customer = new CustomerFakerHelper().createCustomer();

        try (DBFactoryConnection db_conn = new DBFactoryConnection()) {

            this.conn = db_conn.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save() {

        try {
            this.preparedStatement = this.conn.prepareStatement(Queries.insertQuery);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setInt(4, customer.getAge());
            preparedStatement.setLong(5, customer.getAddressId());
            preparedStatement.setBoolean(6, customer.isGdpr());
            preparedStatement.setBoolean(7, customer.isProfileActive());
            preparedStatement.setDate(8, customer.getProfileCreated());
            preparedStatement.setDate(9, customer.getProfileDeactivated());
            preparedStatement.setString(10, customer.getDeactivationReason());
            preparedStatement.setString(11, customer.getNote());

            preparedStatement.executeUpdate();
            System.out.println("Record saved successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update() {
        try {
            // Record with this id will be updated
            int id = getLastId();

            this.preparedStatement = this.conn.prepareStatement(Queries.updateQuery);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.printf("Customer with id = %d was update successfully%n", id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        try {
            // Record with this id will be deleted
            int id = getLastId();
            this.preparedStatement = this.conn.prepareStatement(deleteRecordQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.printf("Customer with id = %d was deleted successfully", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            this.conn.prepareStatement(deleteAllQuery).executeQuery();
            System.out.println("the last id = " + getLastId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getRandomId() {
        int randomId = getRandomNumber(getRecordsCount());
        if (checkIfIdExists(randomId)) {
            return randomId;
        }
        return -1;
    }

    @Override
    public List getRandomIds(int count) {
        int id = 0;
        List<Integer> idsList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            id = getRandomId();
            if (id > -1) {
                // select only unique ids
                if (!idsList.contains(id)) {
                    idsList.add(id);
                } else {
                    // if id has been added to idList we need additional iteration to reach desired count of ids
                    i--;
                }
            } else {
                // if id doesn't exist we need of an additional iteration
                i--;
            }
        }
        return idsList;
    }

    @Override
    public int getRecordsCount() {
        int recordsCount = 0;
        try {
            ResultSet rs = conn.createStatement().executeQuery(Queries.getRecordsCountQuery);
            while (rs.next()) {
                recordsCount = rs.getInt("count");
                if (recordsCount > -1) {
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recordsCount;
    }

    @Override
    public Customer getByID(int id) {
        Statement stmt = null;
        try {

            this.preparedStatement = this.conn.prepareStatement(Queries.selectCustomerQuery);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                Long addressId = rs.getLong("address_id");
                boolean gdpr = rs.getBoolean("gdpr");
                boolean isProfileActive = rs.getBoolean("is_profile_active");

                customer = Customer.builder(name, email, addressId, gdpr, isProfileActive)
                        .phone(rs.getString("phone"))
                        .age(rs.getInt("age"))
                        .addressId(rs.getLong("address_id"))
                        .profileCreated(rs.getDate("profile_created"))
                        .profileDeactivated(rs.getDate("profile_deactivated"))
                        .deactivationReason(rs.getString(10))
                        .note(rs.getString("notes"))
                        .build();
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getByIDs(List<Integer> ids) {
        List<Customer> customers = new ArrayList<>();
        for (int id : ids) {
            customers.add(getByID(id));
        }
        return customers;
    }

    /**
     * Get last id from customer table
     *
     * @return int
     */
    public int getLastId() {
        int id = -1;

        try {
            ResultSet rs = conn.createStatement().executeQuery(Queries.getLastIdQuery);

            while (rs.next()) {
                id = rs.getInt("customer_id");
                if (id > -1) {
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    /**
     * Check if given id exists in customers table
     *
     * @param id int
     * @return boolean
     */
    private boolean checkIfIdExists(int id) {
        try {
            this.preparedStatement = this.conn.prepareStatement(checkIfIdExistsQuery);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if (rs.getInt("count") > 0) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates random integer number
     *
     * @param max maximum value
     * @return int
     */
    private int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

//    @Override
//    public Customer getByID(int id) {
//        try {
//            Object[] mappedCustomer = mapCustomer(id);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        Customer customer;
//
//        try {
//            this.preparedStatement = this.conn.prepareStatement(checkIfIdExistsQuery);
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//
//
//            return null;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * Convert the first row into an Object[].
//     * Map row fro
//     *
//     * @return Object[]
//     * @throws SQLException
//     */
//    public Object[] mapCustomer(int id) throws SQLException {
//        QueryRunner queryRunner = new QueryRunner();
//        // Create a ResultSetHandler
//        ResultSetHandler<Object[]> handler = new ResultSetHandler<Object[]>() {
//            public Object[] handle(ResultSet rs) throws SQLException {
//                if (!rs.next()) {
//                    return null;
//                }
//                ResultSetMetaData meta = rs.getMetaData();
//                int cols = meta.getColumnCount();
//                Object[] result = new Object[cols];
//
//                for (int i = 0; i < cols; i++) {
//                    result[i] = rs.getObject(i + 1);
//                }
//                return result;
//            }
//        };
//
//        try {
//            Object[] result = queryRunner.query(conn, Queries.selectCustomerQuery,
//                    handler, id);
//            //Display values
//            System.out.print("Result: " + Arrays.toString(result));
//            return result;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
