package com.beforedeadline.anbuback.web.tag.dto;

import com.beforedeadline.anbuback.web.friend.dto.FriendResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TagDetailResponse {

    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastContractedDateTime;
    private int lastContracted;
    private int contractCycle;
    private Long friendNumber;
    List<FriendResponse> friendResponses;
}
