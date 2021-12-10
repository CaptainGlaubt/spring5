package com.lernholt.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInfo {

    private String id;
    private String creditCardNumber;
    private String ccv;
}
