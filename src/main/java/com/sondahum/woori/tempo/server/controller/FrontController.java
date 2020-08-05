package com.sondahum.woori.tempo.server.controller;

import com.sondahum.woori.tempo.chat.WooriChatController;
import com.sondahum.woori.tempo.metronome.MetronomeController;
import com.sondahum.woori.tempo.server.WooriConfig;
import com.sondahum.woori.tempo.server.packet.WooriHttpHeader;
import com.sondahum.woori.tempo.signal.TeamSignalController;

public class FrontController {
    private WooriController controller;

    public FrontController(WooriConfig configure) {

    }

    public byte[] controllerMapping(WooriHttpHeader request) {
        // todo request 베이스 parse해서 알맞은 controller에 전달.
        String baseURL = request.getUrl().split("/")[0];

        if (baseURL.equals("tempo")) {
            controller = new MetronomeController();
        } else if (baseURL.equals("chat")) {
            controller = new WooriChatController();
        } else if (baseURL.equals("signal")) {
            controller = new TeamSignalController();
        } else
            throw new IllegalArgumentException();

        return controller.requestMapping(request.getUrl());
    }
}
