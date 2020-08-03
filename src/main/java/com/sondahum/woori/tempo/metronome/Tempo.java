package com.sondahum.woori.tempo.metronome;

import lombok.Getter;

import java.util.concurrent.Callable;

@Getter
public class Tempo implements Callable<Boolean> {
    private final Long MINUTE = 60000L;

    private int bpm = 60;
    private long beatDuration = 1000L;
    private int beatPerBar = 4;

    public void setTempo(int bpm, int bpb) {
        this.bpm = bpm;
        this.beatPerBar = bpb;
        this.beatDuration = MINUTE / bpm;
    }

    public void count() {

    }

    @Override
    public Boolean call() throws Exception {
        return null;
    }
}
