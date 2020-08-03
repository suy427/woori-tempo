package com.sondahum.woori.tempo.metronome;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MetronomeService {
    private final Tempo tempo = new Tempo();
    private final StopWatch stopWatch = new StopWatch();


    public void setTempo() {

    }

    private void setTempo(TempoDto dto) {
        tempo.setTempo(dto.bpm, dto.bpb);
    }


    public void startCount(TempoDto dto) throws InterruptedException {
        stopWatch.reset();
        setTempo(dto);
        stopWatch.start();
        System.out.println("start!!");

        System.out.print("Count in :");
        for (int i = 1; i <= tempo.getBeatPerBar(); i++) {
            Thread.sleep(tempo.getBeatDuration());
            System.out.printf(" %d", i);
        }
        System.out.println("");
        while (true) {
            for (int i = 1; i <= tempo.getBeatPerBar(); i++) {
                System.out.println("tick --> " + i);
                Thread.sleep(tempo.getBeatDuration());
            }
        }
    }

}
