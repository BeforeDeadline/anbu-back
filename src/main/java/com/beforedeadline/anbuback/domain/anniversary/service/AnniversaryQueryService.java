package com.beforedeadline.anbuback.domain.anniversary.service;

import com.beforedeadline.anbuback.domain.account.exception.WrongOwnerException;
import com.beforedeadline.anbuback.domain.anniversary.Anniversary;
import com.beforedeadline.anbuback.domain.anniversary.AnniversaryRepository;
import com.beforedeadline.anbuback.domain.common.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnniversaryQueryService {

    private final AnniversaryRepository anniversaryRepository;

    public List<Anniversary> findByAccount(Long accountId){
        return anniversaryRepository.findByAccount(accountId);
    }

    public Anniversary findById(Long anniversaryId, Long accountId){
        Anniversary anniversary = anniversaryRepository.findById(anniversaryId).orElseThrow(() -> new NotFoundDataException("anniversaryId", String.valueOf(anniversaryId)));
        if(!anniversary.getAccount().getId().equals(accountId)){
            throw new WrongOwnerException("anniversary");
        }
        return anniversary;
    }

    public List<Anniversary> findTodayAnniversary(Long accountId){
        return findByAccount(accountId)
                .stream().filter(anniversary -> anniversary.getDate().isEqual(LocalDate.now()))
                .collect(Collectors.toList());
    }
}
