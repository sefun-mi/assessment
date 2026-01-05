package com.assessment.api_design.data.actions.deposit.DAS;

import com.assessment.api_design.common.enums.TransactionStatus;
import com.assessment.api_design.data.actions.deposit.DTO.DepositDTO;
import com.assessment.api_design.data.domain.transaction.model.Transaction;
import com.assessment.api_design.data.domain.transaction.repository.TransactionRepository;
import com.assessment.api_design.data.domain.wallet.model.Wallet;
import com.assessment.api_design.data.domain.wallet.repository.WalletRepository;
import com.assessment.api_design.interactor.deposit.api.request.DepositRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class DepositDAS {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public DepositDTO initialize(DepositRequest depositRequest){
        Wallet wallet = fetchWallet(depositRequest.getEmail());
        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setStatus(TransactionStatus.PENDING);
        transaction.setCredit(depositRequest.getAmount());
        transaction.setPaymentGateway(depositRequest.getPaymentGateway());
        transaction.setNarration(depositRequest.getNarration());
        transactionRepository.save(transaction);

        return new DepositDTO(transaction.getReference());
    }

    public void complete(boolean successful, String reference){
        if(successful){
            Transaction transaction = fetchTransaction(reference);
            Wallet wallet = transaction.getWallet();

            BigDecimal newBalance = wallet.getBalance().add(transaction.getCredit());
            wallet.setBalance(newBalance);
            walletRepository.save(wallet);

            transaction.setStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transaction);
        }else {
            Transaction transaction = fetchTransaction(reference);
            transaction.setStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
        }


    }

    private Transaction fetchTransaction(String reference){
        return transactionRepository.findByReference(reference)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
    }

    private Wallet fetchWallet(String email){
        return walletRepository.findByUserEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found"));
    }
}