package com.sondahum.woori.tempo.server;

import com.sondahum.woori.tempo.metronome.MetronomeService;

public class WooriController extends AbstractController {
    private WooriService metronome;

    public WooriController(WooriConfig configure) {
        configure = configure;
        _BASEURL = configure.getBaseDir();
        metronome = new MetronomeService();
    }

    @Override
    byte[] doService() {
        return new byte[0];
    }
}
