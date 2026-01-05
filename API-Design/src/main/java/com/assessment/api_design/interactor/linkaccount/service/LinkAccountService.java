package com.assessment.api_design.interactor.linkaccount.service;

import com.assessment.api_design.data.actions.linkaccount.DAS.LinkAccountDAS;
import com.assessment.api_design.interactor.linkaccount.api.request.LinkAccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkAccountService {
    private final LinkAccountDAS linkAccountDAS;

    public void linkAccount(LinkAccountRequest linkAccountRequest){
        linkAccountDAS.linkAccount(linkAccountRequest);
    }
}