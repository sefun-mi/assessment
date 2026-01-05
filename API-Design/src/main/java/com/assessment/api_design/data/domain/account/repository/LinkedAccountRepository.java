package com.assessment.api_design.data.domain.account.repository;


import com.assessment.api_design.data.domain.account.model.LinkedAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkedAccountRepository extends JpaRepository<LinkedAccount, Long> {
    Page<LinkedAccount> findByWalletWalletId(String walletId, Pageable pageable);
    boolean existsByAccountNumberAndBankCode(String accountNumber, String bankCode);
}
