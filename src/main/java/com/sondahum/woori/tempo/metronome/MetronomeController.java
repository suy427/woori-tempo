package com.sondahum.woori.tempo.metronome;

import com.sondahum.woori.tempo.server.WooriController;

public class MetronomeController implements WooriController {

    @Override
    public byte[] requestMapping(String url) {
        String request = url.split("/")[1];
        byte[] response;

        if (request.equals("start")) {
            response = start(url); // note. dto 들어가야함.
        } else if (request.equals("stop")) {
            response = stop();
        } else
            throw new IllegalArgumentException();

        return response;
    }

    private byte[] start(String url) {
        return null;
    }

    private byte[] stop() {
        return null;
    }
}
