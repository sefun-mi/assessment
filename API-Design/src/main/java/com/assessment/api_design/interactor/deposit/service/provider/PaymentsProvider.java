package com.assessment.api_design.interactor.deposit.service.provider;


import com.assessment.api_design.data.domain.transaction.DTO.TransactionDTO;
import com.assessment.api_design.interactor.deposit.api.request.DepositRequest;


public interface PaymentsProvider {
    Object initialize(String reference, DepositRequest depositRequest);

    Object proceed(TransactionDTO transactionDTO);

    boolean complete(TransactionDTO transactionDTO);
}
