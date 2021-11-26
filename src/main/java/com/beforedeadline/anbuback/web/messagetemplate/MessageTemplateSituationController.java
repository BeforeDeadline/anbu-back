package com.beforedeadline.anbuback.web.messagetemplate;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateGroup;
import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateSituation;
import com.beforedeadline.anbuback.domain.messagetemplate.service.MessageTemplateGroupService;
import com.beforedeadline.anbuback.domain.messagetemplate.service.MessageTemplateSituationService;
import com.beforedeadline.anbuback.web.account.annotation.Login;
import com.beforedeadline.anbuback.web.messagetemplate.dto.MessageTemplateSituationResponse;
import com.beforedeadline.anbuback.web.messagetemplate.dto.MessageTemplateSituationSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message-template/groups/{groupId}/situations")
public class MessageTemplateSituationController {

    private final MessageTemplateGroupService messageTemplateGroupService;
    private final MessageTemplateSituationService messageTemplateSituationService;

    @PostMapping
    public Long messageTemplateSituationSave(@Login Account account, @RequestBody MessageTemplateSituationSaveRequest request, @PathVariable Long groupId) {
        MessageTemplateGroup messageTemplateGroup = messageTemplateGroupService.findById(groupId);

        MessageTemplateSituation messageTemplateSituation = MessageTemplateSituation.builder()
                .name(request.getName())
                .messageTemplateGroup(messageTemplateGroup)
                .build();

        messageTemplateSituationService.save(messageTemplateSituation);

        return messageTemplateSituation.getId();
    }

    @GetMapping
    public List<MessageTemplateSituationResponse> getSituations(@Login Account account, @PathVariable Long groupId){
        return messageTemplateSituationService.findByGroupId(groupId).stream().map(mts ->
                MessageTemplateSituationResponse.builder()
                        .id(mts.getId())
                        .name(mts.getName())
                        .build()).collect(Collectors.toList());
    }
}
