package com.assessment.api_design.interactor.deposit.service.provider;

import com.assessment.api_design.data.domain.transaction.DTO.TransactionDTO;
import com.assessment.api_design.interactor.deposit.api.request.DepositRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("PAYSTACK")
@RequiredArgsConstructor
public class PaystackService implements PaymentsProvider{
    @Override
    public Object initialize(DepositRequest depositRequest) {
        log.info("paystack processing");
        log.info("============== initializing transaction");

        return "\"accessCode\":\"access_code\"";
    }

    @Override
    public Object proceed(TransactionDTO transactionDTO) {
        log.info("paystack processing");
        log.info("============== do-nothing");
        return null;
    }

    @Override
    public boolean complete(TransactionDTO transactionDTO) {
        log.info("paystack processing");
        log.info("============== confirming transaction success");
        return true;
    }
}