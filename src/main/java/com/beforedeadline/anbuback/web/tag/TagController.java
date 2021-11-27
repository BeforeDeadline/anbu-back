package com.beforedeadline.anbuback.web.tag;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.tag.entity.FriendTag;
import com.beforedeadline.anbuback.domain.tag.service.FriendTagService;
import com.beforedeadline.anbuback.domain.tag.entity.Tag;
import com.beforedeadline.anbuback.domain.tag.service.TagQueryService;
import com.beforedeadline.anbuback.domain.tag.service.TagService;
import com.beforedeadline.anbuback.web.account.annotation.Login;
import com.beforedeadline.anbuback.web.friend.dto.FriendResponse;
import com.beforedeadline.anbuback.web.tag.dto.SaveFriendToTagRequest;
import com.beforedeadline.anbuback.web.tag.dto.SaveTagRequest;
import com.beforedeadline.anbuback.web.tag.dto.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private final TagQueryService tagQueryService;
    private final FriendTagService friendTagService;

    // todo 태그 상세조회

    @GetMapping("/d-day")
    public List<TagResponse> getDDayTag(@Login Account account) {
        return tagQueryService.findAllDDayTags(account.getId()).stream().map(tag -> TagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .lastContractedDateTime(tag.getLastContractedDateTime())
                .lastContracted(Period.between(tag.getLastContractedDateTime().toLocalDate(), LocalDate.now()).getDays())
                .contractCycle(tag.getContractCycle())
                .friendNumber(friendTagService.countFriendTagByTag(tag.getId()))
                .build()).collect(Collectors.toList());
    }

    @GetMapping
    public List<TagResponse> getTag(@Login Account account) {
        return tagQueryService.findByAccount(account.getId()).stream().map(tag -> TagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .lastContractedDateTime(tag.getLastContractedDateTime())
                .lastContracted(Period.between(tag.getLastContractedDateTime().toLocalDate(), LocalDate.now()).getDays())
                .contractCycle(tag.getContractCycle())
                .friendNumber(friendTagService.countFriendTagByTag(tag.getId()))
                .build()).collect(Collectors.toList());
    }

    @PostMapping
    public Long saveTag(@Login Account account, @RequestBody SaveTagRequest saveTagRequest) {
        Tag group = Tag.builder()
                .name(saveTagRequest.getName())
                .lastContractedDateTime(saveTagRequest.getLastContractedDateTime())
                .contractCycle(saveTagRequest.getContractCycle())
                .account(account)
                .build();

        Tag saved = tagService.save(group);

        return saved.getId();
    }

    @PostMapping("/friends")
    public Long saveFriendToTag(@Login Account account, @RequestBody SaveFriendToTagRequest saveFriendToTagRequest) {

        Long friendId = saveFriendToTagRequest.getFriendId();
        Long tagId = saveFriendToTagRequest.getTagId();

        FriendTag friendTag = tagService.saveFriendToTag(friendId, tagId, account.getId());
        return friendTag.getId();
    }

    @GetMapping("/{tagId}")
    public List<FriendResponse> friendFromTag(@Login Account account, @PathVariable Long tagId) {
        return tagQueryService.findFriendFromTag(tagId).stream().map(friend -> {
            return FriendResponse.builder()
                    .id(friend.getId())
                    .name(friend.getName())
                    .phoneNumber(friend.getPhoneNumber())
                    .contractCycle(friend.getContactCycle())
                    .lastContactedDate(friend.getLastContactedDate())
                    .birthday(friend.getBirthday())
                    .lastContracted(Period.between(friend.getLastContactedDate().toLocalDate(), LocalDate.now()).getDays())
                    .build();
        }).collect(Collectors.toList());
    }


    @PostMapping("/{tagId}/contact")
    public String contactTag(@Login Account account, @PathVariable Long tagId){
        tagService.contact(tagId, account.getId());
        return "ok";
    }
}
