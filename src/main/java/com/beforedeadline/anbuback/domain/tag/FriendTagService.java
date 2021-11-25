package com.beforedeadline.anbuback.domain.tag;

import com.beforedeadline.anbuback.domain.friend.Friend;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendTagService {

    private final FriendTagRepository friendTagRepository;

    public Long countFriendTagByTag(Tag tag){
        return friendTagRepository.countFriendTagByTag(tag);
    }

    public List<FriendTag> findByTag(Tag tag){
        return friendTagRepository.findByTag(tag);
    }

    public List<FriendTag> findByFriend(Friend friend) {
        return friendTagRepository.findByFriend(friend);
    }

    @Transactional
    public FriendTag save(FriendTag friendTag){
        return friendTagRepository.save(friendTag);
    }
}
