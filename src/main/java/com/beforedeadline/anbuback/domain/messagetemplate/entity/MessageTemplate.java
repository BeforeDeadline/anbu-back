package com.beforedeadline.anbuback.domain.messagetemplate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder @AllArgsConstructor
@NoArgsConstructor
public class MessageTemplate {

    @Id @GeneratedValue
    private Long id;

    private String body;
}
