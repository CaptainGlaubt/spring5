package com.lernholt.domain.user;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationInfo {

    private String  id;
    private Date    registeredAt;
    private boolean isActive;
}
