package helpers.customer.helper;

import pojo.lombok.javafaker.customer.Customer;

import java.sql.Date;


public interface CustomerInterface {
    long d = System.currentTimeMillis();
    Date CUSTOMER_DATE_OF_PROFILE_CREATION = new Date(0);
    Date CUSTOMER_DATE_OF_PROFILE_DEACTIVATION = new Date(d);

    Customer createCustomer();
}
