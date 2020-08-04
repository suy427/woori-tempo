package com.sondahum.woori.tempo.metronome;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MetronomeService {
    private final Tempo tempo = new Tempo();

    // note | TAP으로 tempo setting등은 front에서 해야하는 일이다.
    private void setTempo(TempoDto dto) {
        tempo.setTempo(dto.bpm, dto.bpb);
    }


    public void startCount(TempoDto dto) throws InterruptedException {
        setTempo(dto);
        countIn();

        for (;;) {
            int i = 1;
            for (boolean rhythm : tempo.getRhythm()) {
                System.out.println(rhythm + " : " + i++);
                Thread.sleep(tempo.getBeatDuration());
            }
        }
    }

    public void countIn() {

    }

}
