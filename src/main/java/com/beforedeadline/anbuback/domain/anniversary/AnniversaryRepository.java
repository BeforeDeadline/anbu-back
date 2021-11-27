package com.beforedeadline.anbuback.domain.anniversary;

import com.beforedeadline.anbuback.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnniversaryRepository extends JpaRepository<Anniversary, Long> {

    @Query("select a from Anniversary a where a.account.id=:accountId")
    public List<Anniversary> findByAccount(Long accountId);
}
