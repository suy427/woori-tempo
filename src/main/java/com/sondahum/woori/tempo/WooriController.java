package com.sondahum.woori.tempo;

import com.sondahum.woori.tempo.metronome.MetronomeService;
import com.sondahum.woori.tempo.metronome.TempoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/tempo")
@RestController
public class WooriController {

    private final MetronomeService metronomeService;

    public WooriController(MetronomeService service) {
        this.metronomeService = service;
    }

    @GetMapping("/count")
    public void count(TempoDto tempo) throws InterruptedException {
        metronomeService.startCount(tempo);
    }
}
