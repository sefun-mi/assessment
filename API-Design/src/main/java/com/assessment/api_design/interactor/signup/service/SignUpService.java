package com.assessment.api_design.interactor.signup.service;

import com.assessment.api_design.common.mapper.MapperUtil;
import com.assessment.api_design.data.actions.signup.DAS.SignUpDAS;
import com.assessment.api_design.data.actions.signup.DTO.SignUpDTO;
import com.assessment.api_design.interactor.signup.api.request.UserSignUpRequest;
import com.assessment.api_design.interactor.signup.api.response.UserSignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final SignUpDAS signUpDAS;

    public UserSignUpResponse onboard(UserSignUpRequest userSignUpRequest){
        SignUpDTO signUpDTO = signUpDAS.onboardUser(userSignUpRequest);
        UserSignUpResponse response = new UserSignUpResponse();
        MapperUtil.copyPresentProperties(signUpDTO, response);
        return response;
    }
}