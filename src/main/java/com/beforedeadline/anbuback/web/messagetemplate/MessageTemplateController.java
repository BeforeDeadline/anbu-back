package com.beforedeadline.anbuback.web.messagetemplate;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplate;
import com.beforedeadline.anbuback.domain.messagetemplate.service.MessageTemplateService;
import com.beforedeadline.anbuback.web.account.annotation.Login;
import com.beforedeadline.anbuback.web.messagetemplate.dto.MessageTemplateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message-template")
public class MessageTemplateController {

    private final MessageTemplateService messageTemplateService;
//
//    @GetMapping
//    public Long save(@Login Account account, @RequestMapping MessageTemplateRequest messageTemplateRequest){
//        List<Long> messageTemplateSituations = messageTemplateRequest.getMessageTemplateSituations();
//
//        MessageTemplate messageTemplate = MessageTemplate.builder()
//                .body(messageTemplateRequest.getBody())
//                .build();
//
//        messageTemplateService.save(messageTemplate, messageTemplateSituations);
//    }
}
