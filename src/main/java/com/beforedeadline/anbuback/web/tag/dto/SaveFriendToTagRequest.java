package com.beforedeadline.anbuback.web.tag.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveFriendToTagRequest {

    private Long friendId;
    private Long tagId;
}
