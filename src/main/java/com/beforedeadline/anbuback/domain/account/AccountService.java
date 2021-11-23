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
        accountRepository.save(account);
    }

    @Transactional
    public Optional<Account> findByEmail(String email){
        return accountRepository.findByEmail(email);
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
