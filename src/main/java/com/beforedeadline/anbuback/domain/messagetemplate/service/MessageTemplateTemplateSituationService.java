package com.beforedeadline.anbuback.domain.messagetemplate.service;


import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateTemplateSituation;
import com.beforedeadline.anbuback.domain.messagetemplate.repository.MessageTemplateTemplateSituationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageTemplateTemplateSituationService {

    private final MessageTemplateTemplateSituationRepository repository;

    @Transactional
    public MessageTemplateTemplateSituation save(MessageTemplateTemplateSituation messageTemplateTemplateSituation){
        return repository.save(messageTemplateTemplateSituation);
    }
}
