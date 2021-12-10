package com.lernholt.domain.user;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String           id;
    private String           firstName;
    private String           middleName;
    private String           lastName;
    private ContactInfo      contactInfo;
    private AddressInfo      addressInfo;
    private PaymentInfo      paymentInfo;
    private RegistrationInfo registrationInfo;

    public UserDetails toUserDetails() {
        return new org.springframework.security.core.userdetails.User(id, firstName, null);
    }
}
