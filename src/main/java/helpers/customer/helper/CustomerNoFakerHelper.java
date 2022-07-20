package helpers.customer.helper;

import pojo.lombok.javafaker.customer.Customer;

public class CustomerNoFakerHelper implements CustomerInterface {
    public Customer createCustomer() {
        //customer test data
        String name = "Josh Banker";
        String email = "j.banker@gmail.com";
        String phone = "998-555-1212";
        int age = 25;
        Long addressId = 2L;
        boolean GDPR = false;
        boolean isProfileActive = false;
        String deactivationReason = "NO reasons available";
        String note = "customer note  not available";

        return Customer.builder(name, email, addressId, GDPR, isProfileActive)
                .phone(phone)
                .age(age)
                .profileCreated(CUSTOMER_DATE_OF_PROFILE_CREATION)
                .profileDeactivated(CUSTOMER_DATE_OF_PROFILE_DEACTIVATION)
                .deactivationReason(deactivationReason)
                .note(note).build();
    }
}
