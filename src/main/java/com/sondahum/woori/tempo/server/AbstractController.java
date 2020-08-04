package com.sondahum.woori.tempo.server;

import com.sondahum.woori.tempo.metronome.MetronomeService;

import java.io.File;

public abstract class AbstractController {
    protected WooriConfig configure;
    protected String _BASEURL;

    public byte[] requestMapping(WooriHttp request) {
        String path = _BASEURL + request.getUrl();
        return requestHandling(path);
    }

    private byte[] requestHandling(String path) {
        File file = new File(path);

        if (!file.exists()) {
            // todo error page 뱉기.
        }
        return doService();
    }

    abstract byte[] doService();
}
