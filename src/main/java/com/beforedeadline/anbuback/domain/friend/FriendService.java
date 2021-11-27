package com.beforedeadline.anbuback.domain.friend;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.account.exception.WrongOwnerException;
import com.beforedeadline.anbuback.domain.common.exception.NotFoundDataException;
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
        Friend friend = repository.findById(friendId).orElseThrow(() -> new NotFoundDataException("friendId", String.valueOf(friendId)));
        if(!friend.getAccount().getId().equals(account.getId())){
            throw new WrongOwnerException("friend");
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
