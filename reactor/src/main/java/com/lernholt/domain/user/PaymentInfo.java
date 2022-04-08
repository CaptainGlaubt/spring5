package com.lernholt.domain.user;

import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("payment_info")
public class PaymentInfo {

    private String id;
    private String creditCardNumber;
    private String ccv;
}
