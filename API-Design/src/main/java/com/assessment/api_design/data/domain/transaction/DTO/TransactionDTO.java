package com.assessment.api_design.data.domain.transaction.DTO;

import com.assessment.api_design.common.enums.PaymentGateway;
import com.assessment.api_design.common.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private BigDecimal credit;
    private BigDecimal debit;
    private String narration;
    private String reference;
    private TransactionStatus status;
    private PaymentGateway paymentGateway;
}