package com.lernholt.domain.user;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("contact_info")
public class ContactInfo {

    @PrimaryKey
    private String id;
    private String workPhoneNumber;
    private String workEmail;
    private String privatePhoneNumber;
    private String privateEmail;
}
