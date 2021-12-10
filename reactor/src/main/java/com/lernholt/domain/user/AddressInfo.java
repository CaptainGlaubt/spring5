package com.lernholt.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressInfo {

    private String id;
    private String state;
    private String postCode;
    private String street;
    private String apartmentNumber;
}
