package com.assessment.api_design.data.domain.user.DAS;


import com.assessment.api_design.common.mapper.MapperUtil;
import com.assessment.api_design.data.domain.user.model.User;
import com.assessment.api_design.data.domain.user.repository.UserRepository;
import com.assessment.api_design.data.domain.wallet.model.Wallet;
import com.assessment.api_design.interactor.signup.api.request.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDAS {
    private final UserRepository userRepository;

}