package com.assessment.api_design.interactor.wallet.controller;

import com.assessment.api_design.common.response.WebResponseBuilder;
import com.assessment.api_design.interactor.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
@Tag(name = "Wallet")
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/")
    public ResponseEntity<Object> retrieve(@RequestParam String email){
        return WebResponseBuilder.buildSuccessResponse(walletService.retrieve(email));
    }
}