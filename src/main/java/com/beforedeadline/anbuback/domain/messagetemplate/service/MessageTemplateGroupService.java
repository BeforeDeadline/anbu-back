package com.beforedeadline.anbuback.domain.messagetemplate.service;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.common.exception.NotFoundDataException;
import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateGroup;
import com.beforedeadline.anbuback.domain.messagetemplate.repository.MessageTemplateGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageTemplateGroupService {

    private final MessageTemplateGroupRepository groupRepository;

    @Transactional
    public MessageTemplateGroup save(MessageTemplateGroup messageTemplateGroup){
        return groupRepository.save(messageTemplateGroup);
    }

    public List<MessageTemplateGroup> findByAccount(Long accountId) {
        return groupRepository.findByAccount(accountId);
    }

    public MessageTemplateGroup findById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> new NotFoundDataException("groupId", String.valueOf(groupId)));
    }
}
