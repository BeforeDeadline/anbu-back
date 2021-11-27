package com.beforedeadline.anbuback.domain.tag.service;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.account.exception.WrongOwnerException;
import com.beforedeadline.anbuback.domain.common.exception.NotFoundDataException;
import com.beforedeadline.anbuback.domain.friend.Friend;
import com.beforedeadline.anbuback.domain.tag.entity.FriendTag;
import com.beforedeadline.anbuback.domain.tag.entity.Tag;
import com.beforedeadline.anbuback.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagQueryService {

    private final TagRepository tagRepository;
    private final FriendTagService friendTagService;

    public List<Tag> findByAccount(Long accountId) {
        return tagRepository.findByAccount(accountId);
    }

    public Tag findById(Long tagId, Long accountId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new NotFoundDataException("tagId", String.valueOf(tagId)));

        if(!tag.getAccount().getId().equals(accountId)){
            throw new WrongOwnerException("tagId");
        }

        return tag;
    }

    public List<Friend> findFriendFromTag(Long tagId){
        List<FriendTag> friendTags = friendTagService.findByTag(tagId);
        return friendTags.stream().map(FriendTag::getFriend).collect(Collectors.toList());
    }

    public List<Tag> findByFriend(Long friendId) {
        List<FriendTag> friendTags = friendTagService.findByFriend(friendId);
        return friendTags.stream().map(FriendTag::getTag).collect(Collectors.toList());
    }

    public List<Tag> findAllDDayTags(Long accountId) {
        return findByAccount(accountId).stream().filter(tag -> {
            LocalDate dDay = tag.getLastContractedDateTime().toLocalDate()
                    .plus(tag.getContractCycle(), ChronoUnit.DAYS);

            return LocalDate.now().isEqual(dDay) || LocalDate.now().isAfter(dDay);
        }).collect(Collectors.toList());
    }
}
