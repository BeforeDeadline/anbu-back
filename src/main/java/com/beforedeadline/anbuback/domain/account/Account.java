package com.beforedeadline.anbuback.domain.account;

import com.beforedeadline.anbuback.domain.common.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends Period {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(unique = true, nullable = false)
    private String nickname;

    public void changePassword(String password){
        this.password = password;
    }
}
