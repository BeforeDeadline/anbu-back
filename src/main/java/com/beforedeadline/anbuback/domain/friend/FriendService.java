package com.beforedeadline.anbuback.domain.friend;

import com.beforedeadline.anbuback.domain.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendService {

    private final FriendRepository repository;

    public List<Friend> findByAccount(Account account){
        return repository.findByAccount(account);
    }

    @Transactional
    public Friend save(Friend friend){
        return repository.save(friend);
    }

    public Friend findById(Long friendId, Account account) {
        Friend friend = repository.findById(friendId).orElseThrow(() -> new IllegalArgumentException("제대로 된 friend id를 밉력하세요"));
        if(!friend.getAccount().getId().equals(account.getId())){
            throw new IllegalArgumentException("자신이 가지고 있는 account만 get할 수 있습니다.");
        }
        return friend;
    }

    public List<Friend> findAllDDay(Account account){
        return repository.findByAccount(account).stream().filter(a -> {
            LocalDateTime plus = a.getLastContactedDate().plus(a.getContactCycle(), ChronoUnit.DAYS);
            return plus.isBefore(LocalDateTime.now());
        }).collect(Collectors.toList());
    }

    public List<Friend> findBirthday(Account account){
        return repository.findByAccount(account).stream().filter(a -> {
            LocalDate birthdate = a.getBirthday().toLocalDate();
            LocalDate now = LocalDate.now();
            return birthdate.isEqual(now);
        }).collect(Collectors.toList());
    }

    @Transactional
    public void contact(Long friendId, Account account) {
        Friend friend = findById(friendId, account);
        friend.contact();
    }
}
