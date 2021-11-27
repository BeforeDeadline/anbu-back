package com.beforedeadline.anbuback.web.anniversary;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.anniversary.Anniversary;
import com.beforedeadline.anbuback.domain.anniversary.service.AnniversaryQueryService;
import com.beforedeadline.anbuback.domain.anniversary.service.AnniversaryService;
import com.beforedeadline.anbuback.web.account.annotation.Login;
import com.beforedeadline.anbuback.web.anniversary.dto.AnniversaryRequest;
import com.beforedeadline.anbuback.web.anniversary.dto.AnniversaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/anniversary")
public class AnniversaryController {

    private final AnniversaryService anniversaryService;
    private final AnniversaryQueryService anniversaryQueryService;

    @PostMapping
    public Long save(@Login Account account, @RequestBody AnniversaryRequest anniversaryRequest) {
        Anniversary anniversary = Anniversary.builder()
                .name(anniversaryRequest.getName())
                .date(anniversaryRequest.getDate())
                .account(account)
                .build();

        anniversaryService.save(anniversary);

        return anniversary.getId();
    }

    @GetMapping
    public List<AnniversaryResponse> findAllAnniversary(@Login Account account){
        return anniversaryQueryService.findByAccount(account.getId()).stream().map(anniversary ->
                AnniversaryResponse.builder()
                        .id(anniversary.getId())
                        .name(anniversary.getName())
                        .date(anniversary.getDate())
                        .build()).collect(Collectors.toList());
    }

    @GetMapping("/{anniversaryId}")
    public AnniversaryResponse findOneAnniversary(@Login Account account, @PathVariable Long anniversaryId){
        Anniversary anniversary = anniversaryQueryService.findById(anniversaryId, account.getId());
        return AnniversaryResponse.builder()
                .id(anniversary.getId())
                .name(anniversary.getName())
                .date(anniversary.getDate())
                .build();
    }

    @GetMapping("/today")
    public List<AnniversaryResponse> todayAnniversary(@Login Account account) {
        return anniversaryQueryService.findTodayAnniversary(account.getId()).stream().map(anniversary ->
                AnniversaryResponse.builder()
                        .id(anniversary.getId())
                        .name(anniversary.getName())
                        .date(anniversary.getDate())
                        .build()).collect(Collectors.toList());
    }
}
