package com.assessment.api_design.data.actions.signup.DAS;


import com.assessment.api_design.common.mapper.MapperUtil;
import com.assessment.api_design.data.actions.signup.DTO.SignUpDTO;
import com.assessment.api_design.data.domain.user.model.User;
import com.assessment.api_design.data.domain.user.repository.UserRepository;
import com.assessment.api_design.data.domain.wallet.model.Wallet;
import com.assessment.api_design.data.domain.wallet.repository.WalletRepository;
import com.assessment.api_design.interactor.signup.api.request.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SignUpDAS {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public SignUpDTO onboardUser(UserSignUpRequest signUpRequest){
        User user = new User();
        MapperUtil.copyPresentProperties(signUpRequest, user);
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(BigDecimal.valueOf(0));
        user.setWallet(wallet);
        userRepository.save(user);
        walletRepository.save(wallet);

        return new SignUpDTO(wallet.getWalletId());
    }
}