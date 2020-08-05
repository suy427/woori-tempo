package com.sondahum.woori.tempo.metronome;

import com.sondahum.woori.tempo.server.controller.WooriController;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MetronomeController implements WooriController {

    private MetronomeService metronomeService = new MetronomeService();

    @Override
    public byte[] requestMapping(String url) {
        String request = url.split("/")[1];
        byte[] response;

        if (request.equals("start")) {
            String[] params = parseParameters(url);
            response = start(params); // note. dto 들어가야함.
        } else if (request.equals("stop")) {
            response = stop();
        } else
            throw new IllegalArgumentException();

        return response;
    }

    private String[] parseParameters(String url) {
        return url.substring(url.indexOf('/')).split("/");
    }

    private byte[] start(String... params) { // 1.
        TempoDto dto = new TempoDto();
        dto.bpb = Integer.parseInt(params[0]);
        dto.bpm = Integer.parseInt(params[1]);

        try {
            FutureTask<Void> futureTask = new FutureTask<Void>(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    metronomeService.startCount(dto);
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] stop() {
        return null;
    }
}
