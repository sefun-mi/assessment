package com.assessment.api_design.data.domain.wallet.repository;


import com.assessment.api_design.data.domain.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUserEmail(String email);

    Optional<Wallet> findByWalletId(String walletId);
}