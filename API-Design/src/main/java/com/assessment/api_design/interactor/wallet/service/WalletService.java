package com.assessment.api_design.interactor.wallet.service;

import com.assessment.api_design.common.currency.DenominationUtil;
import com.assessment.api_design.common.mapper.MapperUtil;
import com.assessment.api_design.data.domain.wallet.DAS.WalletDAS;
import com.assessment.api_design.data.domain.wallet.DTO.WalletDTO;
import com.assessment.api_design.interactor.wallet.api.response.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletDAS walletDAS;

    public WalletResponse retrieve(String walletId){
        WalletDTO walletDTO = walletDAS.retrieveWalletByWalletId(walletId);
        WalletResponse walletResponse = new WalletResponse();
        MapperUtil.copyPresentProperties(walletDTO, walletResponse);
        walletResponse.setBalance(DenominationUtil.majorToMinor(walletDTO.getBalance()));
        return walletResponse;
    }
}