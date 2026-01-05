package com.assessment.api_design.interactor.deposit.controller;


import com.assessment.api_design.common.response.WebResponseBuilder;
import com.assessment.api_design.interactor.deposit.api.request.CompletePaymentRequest;
import com.assessment.api_design.interactor.deposit.service.DepositService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deposit")
@Tag(name = "Deposit Callback")
public class DepositCallbackController {
    private final DepositService depositService;

    @PostMapping("/callback")
    ResponseEntity<Object> callback(CompletePaymentRequest completePaymentRequest){
        depositService.callback(completePaymentRequest);
        return WebResponseBuilder.buildSuccessResponse(null);
    }
}