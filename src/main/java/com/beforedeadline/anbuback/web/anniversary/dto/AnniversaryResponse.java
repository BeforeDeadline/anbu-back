package com.beforedeadline.anbuback.web.anniversary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder @AllArgsConstructor
public class AnniversaryResponse {

    private Long id;
    private String name;
    private LocalDate date;
}
