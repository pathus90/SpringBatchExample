package com.pathus90.springbatchexample;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class Scheduler {

    private final BatchLauncher batchLauncher;

    //@Scheduled(fixedDelay = 8000)
    public void perform() throws Exception {
        log.info("Batch programmé pour tourner toutes les 8 secondes");
        batchLauncher.run();
    }
}
