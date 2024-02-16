package com.gamoon.gamoonbe.domain.match.api;

import com.gamoon.gamoonbe.domain.match.appication.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        runMatchingAlgorithm();
    }

//    @Scheduled(cron = "30 1 6 * * *", zone = "Asia/Seoul")
    public void runMatchingAlgorithm() {
        System.out.println("Matching algorithm starts");
        matchingService.match();
        matchingService.grouping();
    }
}