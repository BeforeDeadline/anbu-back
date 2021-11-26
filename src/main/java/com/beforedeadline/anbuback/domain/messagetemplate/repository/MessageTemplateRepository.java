package com.beforedeadline.anbuback.domain.messagetemplate.repository;

import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {
}
