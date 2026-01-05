package com.assessment.api_design.interactor.linkaccount.api.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkAccountRequest {

    @NotBlank(message = "Email is required") //todo or email?
    private String email;

    @NotBlank(message = "Account Name is required")
    private String accountName;

    @NotBlank(message = "Account Number is required")
    @Pattern(regexp = "",message = "Account Number must be in valid NUBAN format")//todo: regex
    private String accountNumber;

    @NotBlank(message = "Bank Code is required")
    @Pattern(regexp = "",message = "Bank Code should be six digits")//todo: regex
    private String bankCode;
}