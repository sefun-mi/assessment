package com.assessment.api_design.interactor.deposit.service.provider;

import com.assessment.api_design.data.domain.transaction.DTO.TransactionDTO;
import com.assessment.api_design.interactor.deposit.api.request.DepositRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service("FLUTTERWAVE")
@RequiredArgsConstructor
public class FlutterwaveService implements PaymentsProvider{
    private String ACCESS_TOKEN;
    @Override
    public Object initialize(DepositRequest depositRequest) {
        log.info("flutterwave processing");
        log.info("============== retrieving authorization");
        log.info("============== encrypting card details");
        log.info("============== posting card details");
        return null;

}

    @Override
    public Object proceed(TransactionDTO transactionDTO) {
        log.info("flutterwave processing");
        log.info("============== encrypting pin");
        log.info("============== posting card pin");
        return null;
    }

    @Override
    public boolean complete(TransactionDTO transactionDTO) {
        log.info("flutterwave processing");
        log.info("============== do-nothing");
        return true;
    }
}