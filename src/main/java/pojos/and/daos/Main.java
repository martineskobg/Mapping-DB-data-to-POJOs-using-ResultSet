package pojos.and.daos;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        // customerDao.save();

        // customerDao.update();

        //  customerDao.delete();

        // TODO remove all constraints from customers table
        // customerDao.deleteAll();

        // System.out.println(customerDao.getRandomId());

        //System.out.println(customerDao.getRandomIds(4));

        //System.out.println(customerDao.getRecordsCount());

        System.out.println(customerDao.getByID(5));
        System.out.println();
        System.out.println(customerDao.getByIDs(customerDao.getRandomIds(2)));


    }
}
