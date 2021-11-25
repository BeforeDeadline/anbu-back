package com.beforedeadline.anbuback.domain.anniversary;

import com.beforedeadline.anbuback.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnniversaryRepository extends JpaRepository<Anniversary, Long> {
    public List<Anniversary> findByAccount(Account account);
}
