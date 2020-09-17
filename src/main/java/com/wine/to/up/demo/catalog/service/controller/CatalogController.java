package com.wine.to.up.demo.catalog.service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/catalog/message")
@Validated
@Slf4j
@RequiredArgsConstructor
public class CatalogController {

    @GetMapping("/all")
    public List<String> getAllMessages() {
        return Arrays.asList("This is the first message", "Second message", "Message with id 3");
    }

    @PostMapping("{messageId}")
    public void printMessages(@PathVariable @Min(0) @Max(2) Integer messageId) throws InterruptedException {
        String message = getAllMessages().get(messageId);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int printNumber = Stream.iterate(1, v -> v + 1)
                .limit(10)
                .map(n -> executorService.submit(() -> {
                    int numOfMessages = 10;
                    for (int j = 0; j < numOfMessages; j++) {
                        System.out.println(message);
                    }
                    return numOfMessages;
                }))
                .map(f -> {
                    try {
                        return f.get();
                    } catch (InterruptedException | ExecutionException e) {
                        log.error("Error while sending in Kafka ", e);
                        return 0;
                    }
                })
                .mapToInt(Integer::intValue)
                .sum();

        Thread.sleep(10000);
        log.info("Printed {} times", printNumber);
    }
}
