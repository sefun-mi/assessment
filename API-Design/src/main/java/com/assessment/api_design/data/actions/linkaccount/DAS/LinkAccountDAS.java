package com.assessment.api_design.data.actions.linkaccount.DAS;


import com.assessment.api_design.common.mapper.MapperUtil;
import com.assessment.api_design.data.domain.account.model.LinkedAccount;
import com.assessment.api_design.data.domain.account.repository.LinkedAccountRepository;
import com.assessment.api_design.data.domain.wallet.model.Wallet;
import com.assessment.api_design.data.domain.wallet.repository.WalletRepository;
import com.assessment.api_design.interactor.linkaccount.api.request.LinkAccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LinkAccountDAS {
    private LinkedAccountRepository linkedAccountRepository;
    private WalletRepository walletRepository;

    public void linkAccount(LinkAccountRequest linkAccountRequest){
        if(linkedAccountRepository.existsByAccountNumberAndBankCode(linkAccountRequest.getAccountNumber(),
                linkAccountRequest.getBankCode())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bank Account has already been linked");
        }

        Wallet wallet = fetchWallet(linkAccountRequest.getEmail());
        LinkedAccount linkedAccount = new LinkedAccount();
        MapperUtil.copyPresentProperties(linkAccountRequest, linkedAccount);
        linkedAccount.setWallet(wallet);
        linkedAccount.setUser(wallet.getUser());
        linkedAccountRepository.save(linkedAccount);

    }

    private Wallet fetchWallet(String email){
        return walletRepository.findByUserEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found"));
    }
}