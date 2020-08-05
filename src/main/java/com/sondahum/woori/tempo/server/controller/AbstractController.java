package com.sondahum.woori.tempo.server.controller;

import com.sondahum.woori.tempo.server.WooriConfig;
import com.sondahum.woori.tempo.server.packet.WooriHttpHeader;

import java.io.File;

public abstract class AbstractController {
    protected WooriConfig configure;
    protected String _BASEURL;

    public byte[] requestMapping(WooriHttpHeader request) {
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
