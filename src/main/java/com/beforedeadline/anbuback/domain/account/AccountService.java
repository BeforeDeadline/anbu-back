package com.beforedeadline.anbuback.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(account.getPassword().getBytes());
            account.changePassword(new String(md.digest()));
        } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
        }

        accountRepository.save(account);
    }

    public Optional<Account> findByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    public Optional<Account> findByNickname(String nickname){
        return accountRepository.findByNickname(nickname);
    }

    public Account findById(Long id){
        return accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("account id를 제대로 입력하세요"));
    }

    @Transactional
    public Account login(String email, String password){
        Account account = findByEmail(email).orElseThrow(() -> new IllegalArgumentException("잘못된 이메일 입력"));

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            String inputDigest = new String(md.digest());
            if(!inputDigest.equals(account.getPassword())){
                throw new IllegalArgumentException("잘못된 패스워드 입력");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return account;
    }

}
