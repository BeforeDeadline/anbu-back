package com.beforedeadline.anbuback.domain.messagetemplate.repository;

import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateGroup;
import com.beforedeadline.anbuback.domain.messagetemplate.entity.MessageTemplateSituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageTemplateSituationRepository extends JpaRepository<MessageTemplateSituation, Long> {
    @Query("select mts from MessageTemplateSituation mts where mts.messageTemplateGroup=:messageTemplateGroup")
    List<MessageTemplateSituation> findByGroupId(MessageTemplateGroup messageTemplateGroup);
}
