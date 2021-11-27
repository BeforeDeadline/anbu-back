package com.beforedeadline.anbuback.domain.anniversary;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.account.AccountService;
import com.beforedeadline.anbuback.domain.account.exception.WrongOwnerException;
import com.beforedeadline.anbuback.domain.common.exception.NotFoundDataException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnniversaryService {

    private final AnniversaryRepository anniversaryRepository;
    private final AccountService accountService;

    public Anniversary save(Anniversary anniversary){
        return anniversaryRepository.save(anniversary);
    }

    public List<Anniversary> findByAccount(Account account){
        return anniversaryRepository.findByAccount(account);
    }

    public Anniversary findById(Long anniversaryId, Long accountId){
        Anniversary anniversary = anniversaryRepository.findById(anniversaryId).orElseThrow(() -> new NotFoundDataException("anniversaryId", String.valueOf(anniversaryId)));
        if(!anniversary.getAccount().getId().equals(accountId)){
            throw new WrongOwnerException("anniversary");
        }
        return anniversary;
    }

    public List<Anniversary> todayAnniversary(Long accountId){
        return findByAccount(accountService.findById(accountId))
                .stream().filter(anniversary -> anniversary.getDate().isEqual(LocalDate.now()))
                .collect(Collectors.toList());

    }
}
