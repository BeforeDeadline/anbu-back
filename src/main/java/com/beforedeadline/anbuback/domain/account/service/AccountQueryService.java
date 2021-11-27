package com.beforedeadline.anbuback.domain.account.service;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.account.AccountRepository;
import com.beforedeadline.anbuback.domain.account.exception.WrongPasswordException;
import com.beforedeadline.anbuback.domain.common.exception.DuplicateDataException;
import com.beforedeadline.anbuback.domain.common.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountQueryService {

    private final AccountRepository accountRepository;

    public Optional<Account> findByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    public Optional<Account> findByNickname(String nickname){
        return accountRepository.findByNickname(nickname);
    }

    public Account findById(Long id){
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundDataException("accountId", String.valueOf(id)));
    }
}
