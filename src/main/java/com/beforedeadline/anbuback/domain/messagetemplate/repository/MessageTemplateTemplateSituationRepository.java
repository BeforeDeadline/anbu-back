package com.beforedeadline.anbuback.domain.messagetemplate.repository;

import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateTemplateSituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTemplateTemplateSituationRepository extends JpaRepository<MessageTemplateTemplateSituation, Long> {
}
