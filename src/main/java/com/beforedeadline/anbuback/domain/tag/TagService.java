package com.beforedeadline.anbuback.domain.tag;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.friend.Friend;
import com.beforedeadline.anbuback.domain.friend.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final FriendTagService friendTagService;
    private final FriendService friendService;

    public List<Tag> findByAccount(Account account) {
        return tagRepository.findByAccount(account);
    }

    public Tag save(Tag group) {
        return tagRepository.save(group);
    }

    public Tag findById(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow(() -> new IllegalArgumentException("잘못된 tag id입력"));
    }

    @Transactional
    public FriendTag saveFriendToTag(Long friendId, Long tagId, Account account){
        Friend friend = friendService.findById(friendId, account);
        Tag tag = findById(tagId);

        FriendTag friendTag = FriendTag.builder()
                .friend(friend)
                .tag(tag)
                .build();
        return friendTagService.save(friendTag);
    }

    public List<Friend> findFriendFromTag(Long tagId){
        Tag tag = findById(tagId);
        List<FriendTag> friendTags = friendTagService.findByTag(tag);
        return friendTags.stream().map(FriendTag::getFriend).collect(Collectors.toList());
    }

    public List<Tag> findByFriend(Long friendId, Account account) {
        Friend friend = friendService.findById(friendId, account);
        List<FriendTag> friendTags = friendTagService.findByFriend(friend);
        return friendTags.stream().map(FriendTag::getTag).collect(Collectors.toList());
    }

    @Transactional
    public void contact(Long tagId, Account account) {
        Tag tag = findById(tagId);
        tag.contact();
    }

    public List<Tag> findAllDDayTags(Account account) {
        return findByAccount(account).stream().filter(tag -> {
            return tag.getLastContractedDateTime()
                    .plus(tag.getContractCycle(), ChronoUnit.DAYS)
                    .isBefore(LocalDateTime.now());
        }).collect(Collectors.toList());
    }
}
