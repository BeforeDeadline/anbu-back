package com.beforedeadline.anbuback.domain.tag.service;

import com.beforedeadline.anbuback.domain.friend.Friend;
import com.beforedeadline.anbuback.domain.tag.entity.FriendTag;
import com.beforedeadline.anbuback.domain.tag.repository.FriendTagRepository;
import com.beforedeadline.anbuback.domain.tag.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendTagService {

    private final FriendTagRepository friendTagRepository;

    public Long countFriendTagByTag(Long tagId){
        return friendTagRepository.countFriendTagByTag(tagId);
    }

    public List<FriendTag> findByTag(Long tagId){
        return friendTagRepository.findByTag(tagId);
    }

    public List<FriendTag> findByFriend(Long friendId) {
        return friendTagRepository.findByFriend(friendId);
    }

    @Transactional
    public FriendTag save(FriendTag friendTag){
        return friendTagRepository.save(friendTag);
    }
}
