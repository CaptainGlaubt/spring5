package com.lernholt.domain.user;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@UserDefinedType("address_info")
public class AddressInfoUDT {

    private final String state;
    private final String postCode;
    private final String street;
    private final String apartmentNumber;
}
