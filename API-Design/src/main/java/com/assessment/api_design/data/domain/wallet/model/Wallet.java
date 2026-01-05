package com.assessment.api_design.data.domain.wallet.model;


import com.assessment.api_design.common.model.BaseEntity;
import com.assessment.api_design.data.domain.transaction.model.Transaction;
import com.assessment.api_design.data.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    @ToString.Exclude
    private User user;

    @OneToMany(
            mappedBy = "wallet",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @OrderBy("createdDate DESC")
    private Set<Transaction> transactions;

    @Column(unique = true, nullable = false)
    private String walletId;

    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal balance;

    @PrePersist
    public void generateReference(){
        this.walletId = UUID.randomUUID().toString();
    }
}