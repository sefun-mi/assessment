package com.assessment.api_design.interactor.deposit.service;


import com.assessment.api_design.common.enums.PaymentGateway;
import com.assessment.api_design.data.actions.deposit.DAS.DepositDAS;
import com.assessment.api_design.data.domain.transaction.DAS.TransactionDAS;
import com.assessment.api_design.data.domain.transaction.DTO.TransactionDTO;
import com.assessment.api_design.interactor.deposit.api.request.CompletePaymentRequest;
import com.assessment.api_design.interactor.deposit.api.request.DepositRequest;
import com.assessment.api_design.interactor.deposit.service.provider.PaymentsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositDAS depositDAS;
    private final TransactionDAS transactionDAS;
    private final ApplicationContext applicationContext;

    public Object initialize(DepositRequest depositRequest){
        depositDAS.initialize(depositRequest);
        return fetchProvider(depositRequest.getPaymentGateway()).initialize(depositRequest);
    }

    public Object proceed(String reference){
        TransactionDTO transactionDTO = fetchTransactionDetails(reference);
        return fetchProvider(transactionDTO.getPaymentGateway()).proceed(transactionDTO);
    }

    public boolean complete(String reference){
        TransactionDTO transactionDTO = fetchTransactionDetails(reference);
        boolean successful = fetchProvider(transactionDTO.getPaymentGateway()).complete(transactionDTO);
        depositDAS.complete(successful, reference);
        return successful;
    }

    public void callback(CompletePaymentRequest completePaymentRequest){
        complete(completePaymentRequest.getReference());
    }

    private TransactionDTO fetchTransactionDetails(String reference){
        return transactionDAS.retrieve(reference); //todo cache
    }

    private PaymentsProvider fetchProvider(PaymentGateway paymentGateway){
        return (PaymentsProvider) applicationContext.getBean(paymentGateway.name());
    }
}