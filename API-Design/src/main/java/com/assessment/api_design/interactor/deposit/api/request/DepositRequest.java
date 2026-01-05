package com.assessment.api_design.interactor.deposit.api.request;


import com.assessment.api_design.common.enums.PaymentGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositRequest {
    private String email;
    private BigDecimal amount;
    private String narration;
    private PaymentGateway paymentGateway;
}