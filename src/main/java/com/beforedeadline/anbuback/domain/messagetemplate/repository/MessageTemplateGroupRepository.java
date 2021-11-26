package com.beforedeadline.anbuback.domain.messagetemplate.repository;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageTemplateGroupRepository extends JpaRepository<MessageTemplateGroup, Long> {
    List<MessageTemplateGroup> findByAccount(Account account);
}
