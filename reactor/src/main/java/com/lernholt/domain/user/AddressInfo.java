package com.lernholt.domain.user;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("address_info")
public class AddressInfo {
    @PrimaryKey
    private String id;
    private String state;
    private String postCode;
    private String street;
    private String apartmentNumber;
}
