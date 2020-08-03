package com.sondahum.woori.tempo.metronome;

public enum Notes {
    Quater(4)
    , Eighth(8)
    , Sixteenth(16)
    , TwentyFourth(24);

    public int value;

    Notes(int value) {
        this.value = value;
    }
}
