package com.lernholt.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactInfo {

    private String id;
    private String workPhoneNumber;
    private String workEmail;
    private String privatePhoneNumber;
    private String privateEmail;
}
