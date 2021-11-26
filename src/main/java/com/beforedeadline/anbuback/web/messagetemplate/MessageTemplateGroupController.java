package com.beforedeadline.anbuback.web.messagetemplate;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateGroup;
import com.beforedeadline.anbuback.domain.messagetemplate.service.MessageTemplateGroupService;
import com.beforedeadline.anbuback.domain.messagetemplate.service.MessageTemplateSituationService;
import com.beforedeadline.anbuback.web.account.annotation.Login;
import com.beforedeadline.anbuback.web.messagetemplate.dto.MessageTemplateGroupRequest;
import com.beforedeadline.anbuback.web.messagetemplate.dto.MessageTemplateGroupResponse;
import com.beforedeadline.anbuback.web.messagetemplate.dto.MessageTemplateSituationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message-template/groups")
public class MessageTemplateGroupController {

    private final MessageTemplateGroupService messageTemplateGroupService;
    private final MessageTemplateSituationService messageTemplateSituationService;

    @PostMapping
    public Long saveGroup(@Login Account account, @RequestBody MessageTemplateGroupRequest messageTemplateGroupRequest){
        MessageTemplateGroup messageTemplateGroup = MessageTemplateGroup.builder()
                .name(messageTemplateGroupRequest.getName())
                .account(account)
                .build();
        messageTemplateGroupService.save(messageTemplateGroup);
        return messageTemplateGroup.getId();
    }

    @GetMapping
    public List<MessageTemplateGroupResponse> getGroups(@Login Account account) {
        return messageTemplateGroupService.findByAccount(account).stream().map(mtg -> MessageTemplateGroupResponse.builder()
                .id(mtg.getId())
                .name(mtg.getName())
                .messageTemplateSituationResponses(
                        messageTemplateSituationService.findByGroupId(mtg.getId()).stream().map(mts ->
                                MessageTemplateSituationResponse.builder()
                                        .id(mts.getId())
                                        .name(mts.getName())
                                        .build()).collect(Collectors.toList())
                )
                .build()).collect(Collectors.toList());
    }


}
