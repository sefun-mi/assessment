package com.assessment.api_design.interactor.signup.controller;


import com.assessment.api_design.common.response.WebResponseBuilder;
import com.assessment.api_design.interactor.signup.api.request.UserSignUpRequest;
import com.assessment.api_design.interactor.signup.service.SignUpService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/onboarding")
@Tag(name = "Onboarding")
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> onboard(@Valid @RequestBody UserSignUpRequest userSignUpRequest){
        return WebResponseBuilder
                .buildResponse("Created Successfully.",
                        true,
                        signUpService.onboard(userSignUpRequest),
                        HttpStatusCode.valueOf(201)
                );
    }
}