package com.assessment.api_design.data.domain.account.model;

import com.assessment.api_design.common.model.BaseEntity;
import com.assessment.api_design.data.domain.user.model.User;
import com.assessment.api_design.data.domain.wallet.model.Wallet;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "linked_account",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_bankcode_accountnumber",
                        columnNames = {"bank_code", "account_number"}
                )
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkedAccount extends BaseEntity {

    @Column(nullable = false)
    private String accountName;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String bankCode;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "wallet_id",
            nullable = false,
            unique = true
    )
    @ToString.Exclude
    private Wallet wallet;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    @ToString.Exclude
    private User user;
}