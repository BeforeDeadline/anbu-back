package com.beforedeadline.anbuback.web.friend.dto;

import com.beforedeadline.anbuback.web.tag.dto.TagResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class FriendResponse {

    private Long id;
    private String name;
    private String phoneNumber;
    private int contractCycle;
    private LocalDateTime lastContactedDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    private int lastContracted;

    private List<TagResponse> tags;
}
