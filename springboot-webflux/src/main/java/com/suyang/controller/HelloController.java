package com.suyang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hello reactive by mono");
    }

    @GetMapping("/hello2")
    public Flux<String> hello2() {
        return Flux.just("Hello reactive by flux");
    }
}
