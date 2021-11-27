package com.beforedeadline.anbuback.web.friend;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.friend.Friend;
import com.beforedeadline.anbuback.domain.friend.service.FriendQueryService;
import com.beforedeadline.anbuback.domain.friend.service.FriendService;
import com.beforedeadline.anbuback.domain.tag.service.TagQueryService;
import com.beforedeadline.anbuback.web.account.annotation.Login;
import com.beforedeadline.anbuback.web.friend.dto.FriendCreateRequest;
import com.beforedeadline.anbuback.web.friend.dto.FriendResponse;
import com.beforedeadline.anbuback.web.tag.dto.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;
    private final FriendQueryService friendQueryService;
    private final TagQueryService tagQueryService;

    @GetMapping
    public List<FriendResponse> getFriendsList(@Login Account account) {

        return friendQueryService.findByAccount(account.getId()).stream().map((friend) -> {
            return FriendResponse.builder()
                    .id(friend.getId())
                    .name(friend.getName())
                    .phoneNumber(friend.getPhoneNumber())
                    .contractCycle(friend.getContactCycle())
                    .lastContactedDate(friend.getLastContactedDate())
                    .birthday(friend.getBirthday())
                    .lastContracted(Period.between(friend.getLastContactedDate().toLocalDate(), LocalDate.now()).getDays())
                    .tags(tagQueryService.findByFriend(friend.getId()).stream().map(tag -> {
                        return TagResponse.builder()
                                .id(tag.getId())
                                .name(tag.getName())
                                .lastContractedDateTime(tag.getLastContractedDateTime())
                                .contractCycle(tag.getContractCycle())
                                .build();
                    }).collect(Collectors.toList()))
                    .build();
        }).collect(Collectors.toList());
    }

    @GetMapping("/{friendId}")
    public FriendResponse getFriend(@Login Account account, @PathVariable Long friendId) {
        Friend friend = friendQueryService.findById(friendId, account.getId());
        return FriendResponse.builder()
                .id(friend.getId())
                .name(friend.getName())
                .phoneNumber(friend.getPhoneNumber())
                .contractCycle(friend.getContactCycle())
                .lastContactedDate(friend.getLastContactedDate())
                .lastContracted(Period.between(friend.getLastContactedDate().toLocalDate(), LocalDate.now()).getDays())
                .tags(tagQueryService.findByFriend(friend.getId()).stream().map(tag -> {
                    return TagResponse.builder()
                            .id(tag.getId())
                            .name(tag.getName())
                            .lastContractedDateTime(tag.getLastContractedDateTime())
                            .contractCycle(tag.getContractCycle())
                            .build();
                }).collect(Collectors.toList()))
                .birthday(friend.getBirthday())
                .build();
    }

    @PostMapping
    public Long createFriend(@Login Account account, @Validated @RequestBody FriendCreateRequest friendCreateRequest, BindingResult bindingResult) {
        Friend friend = Friend.builder()
                .name(friendCreateRequest.getName())
                .phoneNumber(friendCreateRequest.getPhoneNumber())
                .contactCycle(friendCreateRequest.getContractCycle())
                .birthday(friendCreateRequest.getBirthday())
                .lastContactedDate(friendCreateRequest.getLastContractedDate())
                .account(account)
                .build();

        friendService.save(friend);

        return friend.getId();
    }

    @GetMapping("/d-day")
    public List<FriendResponse> getDDayFriends(@Login Account account) {
        return friendQueryService.findDDay(account.getId()).stream().map(friend -> FriendResponse.builder()
                .id(friend.getId())
                .name(friend.getName())
                .phoneNumber(friend.getPhoneNumber())
                .contractCycle(friend.getContactCycle())
                .lastContactedDate(friend.getLastContactedDate())
                .lastContracted(Period.between(friend.getLastContactedDate().toLocalDate(), LocalDate.now()).getDays())
                .birthday(friend.getBirthday())
                .tags(tagQueryService.findByFriend(friend.getId()).stream().map(tag -> {
                    return TagResponse.builder()
                            .id(tag.getId())
                            .name(tag.getName())
                            .lastContractedDateTime(tag.getLastContractedDateTime())
                            .contractCycle(tag.getContractCycle())
                            .build();
                }).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
    }

    @GetMapping("/birthday")
    public List<FriendResponse> getBirthDay(@Login Account account) {
        return friendQueryService.findTodayBirthday(account.getId()).stream().map(friend -> FriendResponse.builder()
                .id(friend.getId())
                .name(friend.getName())
                .phoneNumber(friend.getPhoneNumber())
                .contractCycle(friend.getContactCycle())
                .lastContactedDate(friend.getLastContactedDate())
                .lastContracted(Period.between(friend.getLastContactedDate().toLocalDate(), LocalDate.now()).getDays())
                .birthday(friend.getBirthday())
                .tags(tagQueryService.findByFriend(friend.getId()).stream().map(tag -> {
                    return TagResponse.builder()
                            .id(tag.getId())
                            .name(tag.getName())
                            .lastContractedDateTime(tag.getLastContractedDateTime())
                            .contractCycle(tag.getContractCycle())
                            .build();
                }).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
    }

    @PostMapping("/{friendId}/contact")
    public String contactFriend(@Login Account account, @PathVariable Long friendId){
        friendService.contact(friendId, account.getId());
        return "ok";
    }
}
