package com.sondahum.woori.tempo.metronome;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/tempo")
@RestController
public class Controller {

    private final MetronomeService metronomeService;

    public Controller(MetronomeService service) {
        this.metronomeService = service;
    }

    @GetMapping("/start")
    public void start(TempoDto tempo) throws InterruptedException {
        metronomeService.startCount(tempo);
    }
}
