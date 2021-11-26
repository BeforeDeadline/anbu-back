package com.beforedeadline.anbuback.web.messagetemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageTemplateSituationResponse {

    private Long id;
    private String name;
}
