package com.assessment.api_design.interactor.accounts.controller;

import com.assessment.api_design.common.response.WebResponseBuilder;
import com.assessment.api_design.interactor.accounts.service.LinkedAccountsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "module for operations on accounts linked to a wallet")
public class LinkedAccountsController {
    private final LinkedAccountsService linkedAccountsService;

    @GetMapping("/")
    public ResponseEntity<Object> retrieve(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                           @RequestParam(required = false, defaultValue = "10") int pageSize,
                                           @RequestParam String walletId){
        return WebResponseBuilder.buildSuccessResponse(linkedAccountsService.retrieve(pageNumber, pageSize, walletId));
    }
}