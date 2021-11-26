package com.beforedeadline.anbuback.domain.friend;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.common.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Friend extends Period {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private int contactCycle;

    private LocalDateTime lastContactedDate;

    private LocalDateTime birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public void contact() {
        lastContactedDate = LocalDateTime.now();
    }
}
