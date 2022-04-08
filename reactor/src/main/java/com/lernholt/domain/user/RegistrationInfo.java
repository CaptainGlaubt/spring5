package com.lernholt.domain.user;

import java.util.Date;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("registration_info")
public class RegistrationInfo {
    @PrimaryKey
    private String  id;
    private Date    registeredAt;
    private boolean isActive;
}
