package com.assessment.api_design.data.domain.transaction.model;


import com.assessment.api_design.common.enums.PaymentGateway;
import com.assessment.api_design.common.enums.TransactionStatus;
import com.assessment.api_design.data.domain.wallet.model.Wallet;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Column(nullable = false, unique = true)
    private String reference;

    @Column(columnDefinition = "varchar(50)")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(columnDefinition = "varchar(50)")
    @Enumerated(EnumType.STRING)
    private PaymentGateway paymentGateway;

    @Column(precision = 20, scale = 2)
    private BigDecimal credit;

    @Column(precision = 20, scale = 2)
    private BigDecimal debit;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "wallet_id",
            nullable = false,
            unique = true
    )
    @ToString.Exclude
    private Wallet wallet;

    private String narration;

    @PrePersist
    private void generateReference(){
        this.reference = "tx-".concat(RandomStringUtils.randomAlphanumeric(7)); //short, human-readable/shareable
    }
}