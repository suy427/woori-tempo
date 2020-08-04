package com.sondahum.woori.tempo.signal;

import com.sondahum.woori.tempo.server.WooriController;

public class TeamSignalController implements WooriController {
    @Override
    public byte[] requestMapping(String url) {
        return new byte[0];
    }
}
