package com.beforedeadline.anbuback.domain.account.service;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.account.AccountRepository;
import com.beforedeadline.anbuback.domain.account.exception.WrongPasswordException;
import com.beforedeadline.anbuback.domain.account.service.AccountQueryService;
import com.beforedeadline.anbuback.domain.common.exception.DuplicateDataException;
import com.beforedeadline.anbuback.domain.common.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountQueryService accountQueryService;

    public void save(Account account){
        accountQueryService.findByEmail(account.getEmail()).ifPresent((a) -> {
            throw new DuplicateDataException("email", account.getEmail());
        });
        accountQueryService.findByNickname(account.getNickname()).ifPresent((a) -> {
            throw new DuplicateDataException("nickname", account.getNickname());
        });
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(account.getPassword().getBytes());
            account.changePassword(new String(md.digest()));
        } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
        }

        accountRepository.save(account);
    }

    public Account login(String email, String password){
        Account account = accountQueryService.findByEmail(email).orElseThrow(() -> new NotFoundDataException("email", email));

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            String inputDigest = new String(md.digest());
            if(!inputDigest.equals(account.getPassword())){
                throw new WrongPasswordException();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return account;
    }

}
