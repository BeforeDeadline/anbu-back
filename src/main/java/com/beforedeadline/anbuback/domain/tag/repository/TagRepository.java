package com.beforedeadline.anbuback.domain.tag.repository;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
    List<Tag> findByAccount(Account account);
}
