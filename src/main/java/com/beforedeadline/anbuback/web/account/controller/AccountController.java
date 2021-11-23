package com.beforedeadline.anbuback.web.account.controller;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.account.AccountService;
import com.beforedeadline.anbuback.web.account.dto.SignInRequest;
import com.beforedeadline.anbuback.web.account.dto.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/sign-up")
    public SignInResponse signUp(@Validated @RequestBody SignInRequest signInRequest, BindingResult bindingResult){
        Account account = Account.builder()
                .email(signInRequest.getEmail())
                .nickname(signInRequest.getNickname())
                .password(signInRequest.getPassword())
                .build();

        accountService.save(account);

        return new SignInResponse(account.getId());
    }
}
