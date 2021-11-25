package com.beforedeadline.anbuback.domain.tag;

import com.beforedeadline.anbuback.domain.friend.Friend;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FriendTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
