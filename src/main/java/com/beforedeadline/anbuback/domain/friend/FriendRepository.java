package com.beforedeadline.anbuback.domain.friend;

import com.beforedeadline.anbuback.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select f from Friend f where f.account.id=:accountId")
    List<Friend> findByAccount(@Param("accountId") Long accountId);
}
