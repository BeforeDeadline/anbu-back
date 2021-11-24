package com.beforedeadline.anbuback.web;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.web.account.annotation.Login;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(@Login Account account) throws Exception {
        if(account == null){
            throw new Exception("에러");
        }
        return "home";
    }
}
