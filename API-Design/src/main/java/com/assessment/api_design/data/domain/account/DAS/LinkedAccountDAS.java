package com.assessment.api_design.data.domain.account.DAS;


import com.assessment.api_design.common.mapper.MapperUtil;
import com.assessment.api_design.data.domain.account.model.LinkedAccount;
import com.assessment.api_design.data.domain.account.repository.LinkedAccountRepository;
import com.assessment.api_design.interactor.accounts.api.response.LinkedAccountsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LinkedAccountDAS {
    private final LinkedAccountRepository linkedAccountRepository;

    public PageImpl<LinkedAccountsResponse.AccountsResponseItem> retrieve(String walletId, Pageable pageable){
        Page<LinkedAccount> linkedAccountPage = linkedAccountRepository.findByWalletWalletId(walletId, pageable);
        List<LinkedAccountsResponse.AccountsResponseItem> accountsResponseItems = linkedAccountPage.getContent()
                .stream().map(this::map).collect(Collectors.toList());
        return new PageImpl<>(accountsResponseItems, linkedAccountPage.getPageable(), linkedAccountPage.getTotalElements());
    }

    private LinkedAccountsResponse.AccountsResponseItem map(LinkedAccount linkedAccount){
        LinkedAccountsResponse.AccountsResponseItem accountsResponseItem = new LinkedAccountsResponse.AccountsResponseItem();
        MapperUtil.copyPresentProperties(linkedAccount, accountsResponseItem);
        return accountsResponseItem;
    }
}