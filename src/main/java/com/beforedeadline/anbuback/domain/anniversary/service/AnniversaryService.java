package com.beforedeadline.anbuback.domain.anniversary.service;

import com.beforedeadline.anbuback.domain.anniversary.Anniversary;
import com.beforedeadline.anbuback.domain.anniversary.AnniversaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnniversaryService {

    private final AnniversaryRepository anniversaryRepository;

    public Anniversary save(Anniversary anniversary){
        return anniversaryRepository.save(anniversary);
    }
}
