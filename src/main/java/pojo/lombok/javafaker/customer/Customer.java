package pojo.lombok.javafaker.customer;

import java.sql.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Customer {
    @NonNull
    private final String name;
    @NonNull
    private final String email;
    private String phone;
    @Builder.Default
    private int age = 99;
    @NonNull
    private final Long addressId;
    private final boolean gdpr;
    private final boolean isProfileActive;
    private Date profileCreated;
    private Date profileDeactivated;
    private String deactivationReason;
    private String note;

    // Custom builder for Required Parameters
    public static CustomerBuilder builder(final String name,
                                          final String email,
                                          final Long addressId,
                                          final boolean gdpr,
                                          final boolean isProfileActive) {

        return new CustomerBuilder()
                .name(name)
                .email(email)
                .addressId(addressId)
                .gdpr(gdpr).
                isProfileActive(isProfileActive);
    }
}
