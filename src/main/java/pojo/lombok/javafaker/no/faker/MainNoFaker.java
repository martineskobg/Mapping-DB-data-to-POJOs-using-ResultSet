package pojo.lombok.javafaker.no.faker;

import helpers.customer.helper.CustomerNoFakerHelper;
import pojo.lombok.javafaker.customer.Customer;

public class MainNoFaker {


    public static void main(String[] args) {
        // Create a customer using:
        // Lombok @Builder
        // @Builder.Default
        // Required Parameters to Instance
        Customer customer = new CustomerNoFakerHelper().createCustomer();
        System.out.println(customer);
    }
}
