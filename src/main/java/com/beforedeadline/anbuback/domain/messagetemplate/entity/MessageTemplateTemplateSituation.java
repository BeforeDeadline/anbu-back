package com.beforedeadline.anbuback.domain.messagetemplate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageTemplateTemplateSituation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_template_id")
    private MessageTemplate messageTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_template_situation_id")
    private MessageTemplateSituation messageTemplateSituation;
}
