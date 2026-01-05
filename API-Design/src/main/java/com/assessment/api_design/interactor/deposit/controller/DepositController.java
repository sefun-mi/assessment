package com.assessment.api_design.interactor.deposit.controller;

import com.assessment.api_design.common.response.WebResponseBuilder;
import com.assessment.api_design.interactor.deposit.api.request.DepositRequest;
import com.assessment.api_design.interactor.deposit.service.DepositService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deposit")
@Tag(name = "Deposit")
public class DepositController {
    private final DepositService depositService;

    @PostMapping("/initialize")
    ResponseEntity<Object> initialize(@Valid @RequestBody DepositRequest depositRequest){
        return WebResponseBuilder.buildSuccessResponse(depositService.initialize(depositRequest));
    }

    @PostMapping("/proceed")
    ResponseEntity<Object> proceed(@RequestParam String reference){
        return WebResponseBuilder.buildSuccessResponse(depositService.proceed(reference));
    }

    @PostMapping("/complete")
    ResponseEntity<Object> complete(@RequestParam String reference){
        return WebResponseBuilder.buildSuccessResponse(depositService.complete(reference));
    }
}