package com.beforedeadline.anbuback.domain.tag.repository;

import com.beforedeadline.anbuback.domain.friend.Friend;
import com.beforedeadline.anbuback.domain.tag.entity.FriendTag;
import com.beforedeadline.anbuback.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendTagRepository extends JpaRepository<FriendTag, Long> {

    @Query("select count(ft) from FriendTag ft where ft.tag.id=:tagId")
    public Long countFriendTagByTag(Long tagId);

    @Query("select ft from FriendTag ft where ft.friend.id=:friendId")
    public List<FriendTag> findByFriend(Long friendId);

    @Query("select ft from FriendTag ft where ft.tag.id=:tagId")
    public List<FriendTag> findByTag(Long tagId);
}
