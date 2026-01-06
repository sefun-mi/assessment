package com.assessment.api_design.data.domain.wallet.DAS;

import com.assessment.api_design.common.mapper.MapperUtil;
import com.assessment.api_design.data.domain.wallet.DTO.WalletDTO;
import com.assessment.api_design.data.domain.wallet.model.Wallet;
import com.assessment.api_design.data.domain.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class WalletDAS {
    private final WalletRepository walletRepository;

    public WalletDTO retrieveWallet(String email){
        Wallet wallet = walletRepository.findByUserEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found"));
        WalletDTO walletDTO = new WalletDTO();
        MapperUtil.copyPresentProperties(wallet, walletDTO);
        return walletDTO;
    }

    public WalletDTO retrieveWalletByWalletId(String walletId){
        Wallet wallet = walletRepository.findByWalletId(walletId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found"));
        WalletDTO walletDTO = new WalletDTO();
        MapperUtil.copyPresentProperties(wallet, walletDTO);
        return walletDTO;
    }
}