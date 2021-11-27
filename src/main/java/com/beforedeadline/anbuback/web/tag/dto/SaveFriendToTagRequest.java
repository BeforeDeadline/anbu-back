package com.beforedeadline.anbuback.web.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor
@NoArgsConstructor
public class SaveFriendToTagRequest {

    private Long friendId;
    private Long tagId;
}
