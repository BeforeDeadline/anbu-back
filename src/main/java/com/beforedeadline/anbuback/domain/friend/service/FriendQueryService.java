package com.beforedeadline.anbuback.domain.friend.service;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.account.exception.WrongOwnerException;
import com.beforedeadline.anbuback.domain.common.exception.NotFoundDataException;
import com.beforedeadline.anbuback.domain.friend.Friend;
import com.beforedeadline.anbuback.domain.friend.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendQueryService {

    private final FriendRepository repository;

    public List<Friend> findByAccount(Long accountId){
        return repository.findByAccount(accountId);
    }

    public Friend findById(Long friendId, Long accountId) {
        Friend friend = repository.findById(friendId).orElseThrow(() -> new NotFoundDataException("friendId", String.valueOf(friendId)));
        if(!friend.getAccount().getId().equals(accountId)){
            throw new WrongOwnerException("friend");
        }
        return friend;
    }

    public List<Friend> findDDay(Long accountId){
        return repository.findByAccount(accountId).stream().filter(a -> {
            LocalDate dDay = a.getLastContactedDate().toLocalDate().plus(a.getContactCycle(), ChronoUnit.DAYS);
            return LocalDate.now().isEqual(dDay) || LocalDate.now().isAfter(dDay);
        }).collect(Collectors.toList());
    }

    public List<Friend> findTodayBirthday(Long accountId){
        return repository.findByAccount(accountId).stream().filter(a -> {
            LocalDate birthdate = a.getBirthday().toLocalDate();
            LocalDate now = LocalDate.now();
            return birthdate.isEqual(now);
        }).collect(Collectors.toList());
    }
}
