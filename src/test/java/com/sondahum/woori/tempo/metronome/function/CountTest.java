package com.sondahum.woori.tempo.metronome.function;

import com.sondahum.woori.tempo.metronome.MetronomeService;
import com.sondahum.woori.tempo.metronome.TempoDto;
import org.junit.Before;
import org.junit.Test;


public class CountTest {

    MetronomeService metronomeService;

    @Before
    public void setup() {
        metronomeService = new MetronomeService();
    }

    @Test
    public void count() throws InterruptedException {
        TempoDto dto = new TempoDto();
        dto.bpm = 100;
        dto.bpb = 5;

        metronomeService.startCount(dto);
    }
}
