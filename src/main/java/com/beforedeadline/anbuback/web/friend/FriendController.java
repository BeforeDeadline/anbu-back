package com.beforedeadline.anbuback.web.friend;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.friend.Friend;
import com.beforedeadline.anbuback.domain.friend.FriendService;
import com.beforedeadline.anbuback.domain.tag.TagService;
import com.beforedeadline.anbuback.web.account.annotation.Login;
import com.beforedeadline.anbuback.web.friend.dto.FriendCreateRequest;
import com.beforedeadline.anbuback.web.friend.dto.FriendResponse;
import com.beforedeadline.anbuback.web.tag.dto.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;
    private final TagService tagService;

    @GetMapping
    public List<FriendResponse> getFriendsList (@Login Account account) {

        return friendService.findByAccount(account).stream().map((friend) -> {
            return FriendResponse.builder()
                    .id(friend.getId())
                    .name(friend.getName())
                    .phoneNumber(friend.getPhoneNumber())
                    .contractCycle(friend.getContactCycle())
                    .lastContactedDate(friend.getLastContactedDate())
                    .birthday(friend.getBirthday())
                    .lastContracted(Period.between(friend.getLastContactedDate().toLocalDate(), LocalDate.now()).getDays())
                    .tags(tagService.findByFriend(friend.getId(), account).stream().map(tag -> {
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
    public FriendResponse getFriend(@Login Account account, @PathVariable Long friendId){
        Friend friend = friendService.findById(friendId, account);
        return FriendResponse.builder()
                .id(friend.getId())
                .name(friend.getName())
                .phoneNumber(friend.getPhoneNumber())
                .contractCycle(friend.getContactCycle())
                .lastContactedDate(friend.getLastContactedDate())
                .lastContracted(Period.between(friend.getLastContactedDate().toLocalDate(), LocalDate.now()).getDays())
                .tags(tagService.findByFriend(friend.getId(), account).stream().map(tag -> {
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
    public Long createFriend (@Login Account account, @Validated @RequestBody FriendCreateRequest friendCreateRequest, BindingResult bindingResult) {
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

    @GetMapping("/dday")
    public List<FriendResponse> getDDayFriends(@Login Account account){
        return friendService.findAllDDay(account).stream().map(friend -> FriendResponse.builder()
                .id(friend.getId())
                .name(friend.getName())
                .phoneNumber(friend.getPhoneNumber())
                .contractCycle(friend.getContactCycle())
                .lastContactedDate(friend.getLastContactedDate())
                .lastContracted(Period.between(friend.getLastContactedDate().toLocalDate(), LocalDate.now()).getDays())
                .birthday(friend.getBirthday())
                .tags(tagService.findByFriend(friend.getId(), account).stream().map(tag -> {
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
    public List<FriendResponse> getBirthDay(@Login Account account){
        return friendService.findBirthday(account).stream().map(friend -> FriendResponse.builder()
                .id(friend.getId())
                .name(friend.getName())
                .phoneNumber(friend.getPhoneNumber())
                .contractCycle(friend.getContactCycle())
                .lastContactedDate(friend.getLastContactedDate())
                .lastContracted(Period.between(friend.getLastContactedDate().toLocalDate(), LocalDate.now()).getDays())
                .birthday(friend.getBirthday())
                .tags(tagService.findByFriend(friend.getId(), account).stream().map(tag -> {
                    return TagResponse.builder()
                            .id(tag.getId())
                            .name(tag.getName())
                            .lastContractedDateTime(tag.getLastContractedDateTime())
                            .contractCycle(tag.getContractCycle())
                            .build();
                }).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
    }
}
