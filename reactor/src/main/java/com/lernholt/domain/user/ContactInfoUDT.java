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
@UserDefinedType("contact_info")
public class ContactInfoUDT {
    private final String workPhoneNumber;
    private final String workEmail;
    private final String privatePhoneNumber;
    private final String privateEmail;
}
