package com.sondahum.woori.tempo.server;

public class FrontController {
    private ControllerInterface controller;

    public FrontController(WooriConfig configure) {

    }

    public byte[] requestMapping(WooriHttp request) {
        // todo request 베이스 parse해서 알맞은 controller에 전달.

        return new byte[0];
    }
}
