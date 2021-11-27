package com.beforedeadline.anbuback.domain.account;

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
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void save(Account account){
        findByEmail(account.getEmail()).ifPresent((a) -> {
            throw new DuplicateDataException("email", account.getEmail());
        });
        findByNickname(account.getNickname()).ifPresent((a) -> {
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

    public Optional<Account> findByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    public Optional<Account> findByNickname(String nickname){
        return accountRepository.findByNickname(nickname);
    }

    public Account findById(Long id){
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundDataException("accountId", String.valueOf(id)));
    }

    @Transactional
    public Account login(String email, String password){
        Account account = findByEmail(email).orElseThrow(() -> new NotFoundDataException("email", email));

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
