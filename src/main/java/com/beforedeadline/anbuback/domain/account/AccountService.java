package com.beforedeadline.anbuback.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void save(Account account){
        findByEmail(account.getEmail()).ifPresent((a) -> {
            throw new IllegalArgumentException("이미 등록된 이메일입니다");
        });
        findByNickname(account.getNickname()).ifPresent((a) -> {
            throw new IllegalArgumentException("이미 등록된 닉네임입니다");
        });
        accountRepository.save(account);
    }

    public Optional<Account> findByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    public Optional<Account> findByNickname(String nickname){
        return accountRepository.findByNickname(nickname);
    }

    public Optional<Account> findById(Long id){
        return accountRepository.findById(id);
    }



    @Transactional
    public Account login(String email, String password){
        Account account = findByEmail(email).orElseThrow(() -> new IllegalArgumentException("잘못된 이메일 입력"));
        if(!account.getPassword().equals(password)) {
            throw new IllegalArgumentException("잘못된 패스워드 입력");
        }
        return account;
    }

}
