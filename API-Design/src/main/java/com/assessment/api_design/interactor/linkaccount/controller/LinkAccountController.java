package com.assessment.api_design.interactor.linkaccount.controller;

import com.assessment.api_design.common.response.WebResponseBuilder;
import com.assessment.api_design.interactor.linkaccount.api.request.LinkAccountRequest;
import com.assessment.api_design.interactor.linkaccount.service.LinkAccountService;
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
@RequestMapping("/link-account")
@Tag(name = "Link Account", description = "module for actions linking a bank account to a wallet")
public class LinkAccountController {
    private final LinkAccountService linkAccountService;

    @PostMapping("/")
    public ResponseEntity<Object> linkAccount(@Valid @RequestBody LinkAccountRequest linkAccountRequest){
        linkAccountService.linkAccount(linkAccountRequest);
        return WebResponseBuilder
                .buildResponse("Created Successfully.",
                        true,
                        null,
                        HttpStatusCode.valueOf(201)
                );
    }
}