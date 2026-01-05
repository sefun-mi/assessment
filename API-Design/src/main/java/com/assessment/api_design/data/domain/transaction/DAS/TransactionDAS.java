package com.assessment.api_design.data.domain.transaction.DAS;


import com.assessment.api_design.common.mapper.MapperUtil;
import com.assessment.api_design.data.domain.transaction.DTO.TransactionDTO;
import com.assessment.api_design.data.domain.transaction.model.Transaction;
import com.assessment.api_design.data.domain.transaction.repository.TransactionRepository;
import com.assessment.api_design.interactor.transactions.api.response.TransactionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionDAS {
    private final TransactionRepository transactionRepository;

    public TransactionDTO retrieve(String reference){
        TransactionDTO transactionDTO = new TransactionDTO();
        Transaction transaction = fetchTransaction(reference);
        MapperUtil.copyPresentProperties(transaction, transactionDTO);
        return transactionDTO;
    }

    public PageImpl<TransactionsResponse.TransactionResponseItem> retrieve(String walletId, Pageable pageable){
        Page<Transaction> transactionsPage = transactionRepository.findByWalletWalletId(walletId, pageable);
        List<TransactionsResponse.TransactionResponseItem> transactionResponseItems = transactionsPage.getContent()
                .stream().map(this::map).collect(Collectors.toList());

        return new PageImpl<>(transactionResponseItems, transactionsPage.getPageable(), transactionsPage.getTotalElements());
    }

    private TransactionsResponse.TransactionResponseItem map(Transaction transaction){
        TransactionsResponse.TransactionResponseItem transactionResponseItem = new TransactionsResponse.TransactionResponseItem();
        MapperUtil.copyPresentProperties(transaction, transactionResponseItem);
        if(transaction.getCredit() != null){
            transactionResponseItem.setAmount(transaction.getCredit());
        }else {
            transactionResponseItem.setAmount(transaction.getDebit().negate());
        }
        return transactionResponseItem;
    }

    private Transaction fetchTransaction(String reference){
        return transactionRepository.findByReference(reference)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
    }
}