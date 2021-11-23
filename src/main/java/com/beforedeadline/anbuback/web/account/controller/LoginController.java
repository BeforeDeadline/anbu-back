package com.beforedeadline.anbuback.web.account.controller;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.account.AccountService;
import com.beforedeadline.anbuback.web.account.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;

    @PostMapping("/login")
    public String login(@Validated @RequestBody LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request){
        Account account = accountService.login(loginRequest.getEmail(), loginRequest.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute("id", account.getId());

        return "ok";
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return new ResponseEntity<>("세션이 만료되었습니다", HttpStatus.BAD_REQUEST);
        }
        session.invalidate();
        return ResponseEntity.ok("ok");
    }
}
