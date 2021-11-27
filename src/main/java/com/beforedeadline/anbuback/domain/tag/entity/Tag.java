package com.beforedeadline.anbuback.domain.tag.entity;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.common.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends Period {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String name;

    private LocalDateTime lastContractedDateTime;

    private int contractCycle;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public void contact() {
        lastContractedDateTime = LocalDateTime.now();
    }
}
