package com.assessment.api_design.interactor.linkedaccounts.api.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkedAccountsResponse {
    private Page<AccountsResponseItem> accounts;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountsResponseItem {
        private String accountName;
        private String accountNumber;
        private String bankCode;

    }
}