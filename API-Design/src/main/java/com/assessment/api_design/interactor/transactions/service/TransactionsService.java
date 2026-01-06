package com.assessment.api_design.interactor.transactions.service;

import com.assessment.api_design.common.currency.DenominationUtil;
import com.assessment.api_design.common.mapper.MapperUtil;
import com.assessment.api_design.common.page.CustomPageResponse;
import com.assessment.api_design.common.page.DefaultPageRequest;
import com.assessment.api_design.data.domain.transaction.DAS.TransactionDAS;
import com.assessment.api_design.data.domain.transaction.DTO.TransactionDTO;
import com.assessment.api_design.interactor.transactions.api.response.TransactionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    private final TransactionDAS transactionDAS;

    public TransactionsResponse.TransactionResponseItem retrieve(String reference){
        TransactionDTO transactionDTO = transactionDAS.retrieve(reference);
        TransactionsResponse.TransactionResponseItem transactionResponseItem = new TransactionsResponse.TransactionResponseItem();
        MapperUtil.copyPresentProperties(transactionDTO, transactionResponseItem);
        if(transactionDTO.getCredit() != null){
            transactionResponseItem.setAmount(DenominationUtil.majorToMinor(transactionDTO.getCredit()));
        }else {
            transactionResponseItem.setAmount(DenominationUtil.majorToMinor(transactionDTO.getDebit().negate()));
        }
        return transactionResponseItem;
    }

    public CustomPageResponse<TransactionsResponse.TransactionResponseItem> retrieve(int pageNumber, int pageSize, String walletId){
        Pageable pageable = new DefaultPageRequest(pageNumber, pageSize);
        return new CustomPageResponse<>(transactionDAS.retrieve(walletId, pageable));
    }
}