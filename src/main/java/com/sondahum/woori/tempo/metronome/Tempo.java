package com.sondahum.woori.tempo.metronome;

import lombok.Getter;

@Getter
public class Tempo {
    private final Long MINUTE = 60000L;
    private int bpm = 60;
    private long beatDuration = 1000L;
    private int beatPerBar = 4;

    public void setTempo(int bpm, int beatPerBar) {
        this.bpm = bpm;
        this.beatPerBar = beatPerBar;
        this.beatDuration = MINUTE / bpm;
    }
}
