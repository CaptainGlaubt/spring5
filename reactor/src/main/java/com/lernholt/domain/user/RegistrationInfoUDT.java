package com.lernholt.domain.user;

import java.util.Date;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@UserDefinedType("registration_info")
public class RegistrationInfoUDT {

    private final String  id;
    private final Date    registeredAt;
    private final boolean isActive;
}
