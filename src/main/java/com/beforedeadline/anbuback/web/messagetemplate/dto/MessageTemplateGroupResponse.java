package com.beforedeadline.anbuback.web.messagetemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder @AllArgsConstructor
public class MessageTemplateGroupResponse {

    private Long id;
    private String name;
    private List<MessageTemplateSituationResponse> messageTemplateSituationResponses;
}
