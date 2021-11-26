package com.beforedeadline.anbuback.domain.messagetemplate.service;

import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateGroup;
import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateSituation;
import com.beforedeadline.anbuback.domain.messagetemplate.repository.MessageTemplateSituationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageTemplateSituationService {

    private final MessageTemplateSituationRepository messageTemplateSituationRepository;
    private final MessageTemplateGroupService messageTemplateGroupService;

    @Transactional
    public MessageTemplateSituation save(MessageTemplateSituation messageTemplateSituation) {
        return messageTemplateSituationRepository.save(messageTemplateSituation);
    }

    public List<MessageTemplateSituation> findByGroupId(Long groupId) {
        return messageTemplateSituationRepository.findByGroupId(messageTemplateGroupService.findById(groupId));
    }
}
