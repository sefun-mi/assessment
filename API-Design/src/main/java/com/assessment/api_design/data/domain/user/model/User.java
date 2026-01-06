package com.assessment.api_design.data.domain.user.model;

import com.assessment.api_design.common.model.BaseEntity;
import com.assessment.api_design.data.domain.wallet.model.Wallet;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "wallet_id",
            nullable = false,
            unique = true
    )
    @ToString.Exclude
    private Wallet wallet;
}