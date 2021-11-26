package com.beforedeadline.anbuback.domain.messagetemplate.service;

import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplate;
import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateSituation;
import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateTemplateSituation;
import com.beforedeadline.anbuback.domain.messagetemplate.repository.MessageTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageTemplateService {

    private final MessageTemplateRepository messageTemplateRepository;
    private final MessageTemplateSituationService messageTemplateSituationService;
//
//    @Transactional
//    public MessageTemplate save(MessageTemplate messageTemplate, List<Long> messageTemplateSituations){
//        MessageTemplate saveMessageTemplate = messageTemplateRepository.save(messageTemplate);
//        for (Long messageTemplateSituation : messageTemplateSituations) {
//            messageTemplateSituationService.
//            MessageTemplateTemplateSituation.builder()
//                    .messageTemplate(saveMessageTemplate)
//                    .build()
//        }
//    }
}
