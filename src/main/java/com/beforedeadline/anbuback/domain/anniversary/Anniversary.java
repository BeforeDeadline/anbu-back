package com.beforedeadline.anbuback.domain.anniversary;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.common.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.channels.AcceptPendingException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor @Builder
@NoArgsConstructor
public class Anniversary extends Period {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
