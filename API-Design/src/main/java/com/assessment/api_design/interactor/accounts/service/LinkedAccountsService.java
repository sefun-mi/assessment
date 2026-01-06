package com.assessment.api_design.interactor.accounts.service;

import com.assessment.api_design.common.page.CustomPageResponse;
import com.assessment.api_design.common.page.DefaultPageRequest;
import com.assessment.api_design.data.domain.account.DAS.LinkedAccountDAS;
import com.assessment.api_design.interactor.accounts.api.response.LinkedAccountsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkedAccountsService {
    private final LinkedAccountDAS linkedAccountDAS;

    public CustomPageResponse<LinkedAccountsResponse.AccountsResponseItem> retrieve(int pageNumber, int pageSize, String walletId){
        Pageable pageable = new DefaultPageRequest(pageNumber, pageSize);
        return new CustomPageResponse<>(linkedAccountDAS.retrieve(walletId, pageable));
    }
}