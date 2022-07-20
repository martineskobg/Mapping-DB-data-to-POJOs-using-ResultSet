package helpers.customer.helper;

import com.github.javafaker.Faker;
import pojo.lombok.javafaker.customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerFakerHelper implements CustomerInterface {
    Faker faker = new Faker();
    private final String NAME = faker.name().fullName();
    private final String EMAIL = faker.internet().emailAddress();
    private final String PHONE = faker.phoneNumber().phoneNumber();
    private final int AGE = faker.number().numberBetween(18, 99);
    private final Long ADDRESS_ID = (long) faker.number().randomDigitNotZero();
    private final boolean GDPR = faker.bool().bool();
    private final boolean IS_PROFILE_ACTIVE = faker.bool().bool();

    private final String DEACTIVATED_REASON = faker.lorem().fixedString(200);
    private final String NOTE = faker.lorem().fixedString(200);


    /**
     * Create a single object with random data
     *
     * @return Customer
     */
    public Customer createCustomer() {
        return Customer.builder(this.NAME, this.EMAIL, this.ADDRESS_ID, this.GDPR, this.IS_PROFILE_ACTIVE)
                .phone(this.PHONE)
                .age(this.AGE)
                .profileCreated(CUSTOMER_DATE_OF_PROFILE_CREATION)
                .profileDeactivated(CUSTOMER_DATE_OF_PROFILE_DEACTIVATION)
                .deactivationReason(this.DEACTIVATED_REASON)
                .note(this.NOTE).build();
    }

    /**
     * Creates list of Customers
     *
     * @param numOfCustomers number of desired customers
     * @return А list of X Customers with random data
     */
    public List<Customer> createListOfCustomers(int numOfCustomers) {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < numOfCustomers; i++) {
            customers.add(createCustomer());
        }
        return customers;
    }
}
