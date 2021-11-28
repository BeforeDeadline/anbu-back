package com.beforedeadline.anbuback.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "Anbu(안부) 백엔드 API 서버입니다. 앱을 통해 서비스를 이용해주세요.";
    }
}
