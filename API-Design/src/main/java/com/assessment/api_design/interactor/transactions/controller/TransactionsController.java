package com.assessment.api_design.interactor.transactions.controller;

import com.assessment.api_design.common.response.WebResponseBuilder;
import com.assessment.api_design.interactor.transactions.service.TransactionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
@Tag(name = "Transactions")
public class TransactionsController {
    private final TransactionsService transactionsService;

    @GetMapping("/{reference}")
    public ResponseEntity<Object> retrieve(@PathVariable String reference){
        return WebResponseBuilder.buildSuccessResponse(transactionsService.retrieve(reference));
    }

    @GetMapping("/")
    public ResponseEntity<Object> retrieve(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                           @RequestParam(required = false, defaultValue = "10") int pageSize,
                                           @RequestParam String walletId){
        return WebResponseBuilder.buildSuccessResponse(transactionsService.retrieve(pageNumber, pageSize, walletId));
    }
}