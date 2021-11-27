package com.beforedeadline.anbuback.domain.friend.service;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.friend.Friend;
import com.beforedeadline.anbuback.domain.friend.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FriendService {

    private final FriendRepository repository;
    private final FriendQueryService friendQueryService;

    public Friend save(Friend friend){
        return repository.save(friend);
    }

    public void contact(Long friendId, Long accountId) {
        Friend friend = friendQueryService.findById(friendId, accountId);
        friend.contact();
    }
}
