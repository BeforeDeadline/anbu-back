package com.beforedeadline.anbuback.domain.tag.repository;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);

    @Query("select t from Tag t where t.account.id=:accountId")
    List<Tag> findByAccount(@Param("accountId") Long accountId);
}
