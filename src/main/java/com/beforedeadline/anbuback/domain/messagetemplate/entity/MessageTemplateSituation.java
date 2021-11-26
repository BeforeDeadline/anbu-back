package com.beforedeadline.anbuback.domain.messagetemplate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder @AllArgsConstructor
@NoArgsConstructor
public class MessageTemplateSituation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_template_group_id")
    private MessageTemplateGroup messageTemplateGroup;
}
