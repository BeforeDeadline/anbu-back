package com.beforedeadline.anbuback.domain.tag;

import com.beforedeadline.anbuback.domain.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendTagRepository extends JpaRepository<FriendTag, Long> {

    @Query("select count(ft) from FriendTag ft where ft.tag=:tag")
    public Long countFriendTagByTag(Tag tag);

    public List<FriendTag> findByFriend(Friend friend);
    public List<FriendTag> findByTag(Tag friend);
}
