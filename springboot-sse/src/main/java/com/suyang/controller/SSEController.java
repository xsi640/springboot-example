package com.suyang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class SSEController {
    private List<SseEmitter> emitters = new ArrayList<>();

    @RequestMapping(path = "/stream", method = RequestMethod.GET)
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        return emitter;
    }

    @ResponseBody
    @RequestMapping(path = "/chat", method = RequestMethod.POST)
    public Message sendMessage(@Valid Message message) {
        log.info("Got message$message");
        emitters.forEach((emitter) -> {
            try {
                emitter.send(message, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                emitter.complete();
                emitters.remove(emitter);
                log.error(e.getMessage(), e);
            }
        });
        return message;
    }

}
