package com.beforedeadline.anbuback.domain.tag.service;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.friend.Friend;
import com.beforedeadline.anbuback.domain.friend.service.FriendQueryService;
import com.beforedeadline.anbuback.domain.tag.entity.FriendTag;
import com.beforedeadline.anbuback.domain.tag.entity.Tag;
import com.beforedeadline.anbuback.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final TagQueryService tagQueryService;

    private final FriendQueryService friendQueryService;
    private final FriendTagService friendTagService;

    public Tag save(Tag group) {
        return tagRepository.save(group);
    }

    public FriendTag saveFriendToTag(Long friendId, Long tagId, Long accountId){
        Friend friend = friendQueryService.findById(friendId, accountId);
        Tag tag = tagQueryService.findById(tagId, accountId);

        FriendTag friendTag = FriendTag.builder()
                .friend(friend)
                .tag(tag)
                .build();
        return friendTagService.save(friendTag);
    }

    public void contact(Long tagId, Long accountId) {
        Tag tag = tagQueryService.findById(tagId, accountId);
        tag.contact();
    }
}
