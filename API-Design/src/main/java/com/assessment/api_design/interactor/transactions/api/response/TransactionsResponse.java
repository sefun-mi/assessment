package com.assessment.api_design.interactor.transactions.api.response;

import com.assessment.api_design.common.enums.PaymentGateway;
import com.assessment.api_design.common.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsResponse {
    private Page<TransactionResponseItem> transactions;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TransactionResponseItem {
        private BigDecimal amount;
        private String narration;
        private String reference;
        private TransactionStatus status;
        private PaymentGateway paymentGateway;

    }
}