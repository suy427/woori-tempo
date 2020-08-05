package com.sondahum.woori.tempo.server.controller;

import com.sondahum.woori.tempo.chat.WooriChatController;
import com.sondahum.woori.tempo.metronome.MetronomeController;
import com.sondahum.woori.tempo.server.WooriConfig;
import com.sondahum.woori.tempo.server.packet.WooriHttpRequest;
import com.sondahum.woori.tempo.server.packet.WooriHttpResponse;
import com.sondahum.woori.tempo.signal.TeamSignalController;

import java.io.*;
import java.net.Socket;

public class FrontController {
    private WooriController controller;
    private WooriConfig configure;

    public FrontController(WooriConfig configure) {
        this.configure = configure;
    }

    public WooriHttpResponse controllerMapping(WooriHttpRequest request) throws IOException {
        // todo request 베이스 parse해서 알맞은 controller에 전달.
        String baseURL = request.getHeader().getUrl().split("/")[0];
        byte[] result;
        if (baseURL.equals("/")) {

        } else if (baseURL.equals("tempo")) {
            controller = new MetronomeController();
        } else if (baseURL.equals("chat")) {
            controller = new WooriChatController();
        } else if (baseURL.equals("signal")) {
            controller = new TeamSignalController();
        } else            throw new IllegalArgumentException();

        result = controller.handleRequest(request.getHeader().getUrl());
        return createResponse(request, result);
    }

    public WooriHttpResponse createResponse(WooriHttpRequest request, byte[] result) throws IOException {
        WooriHttpResponse response = new WooriHttpResponse();
        StringBuilder sb = new StringBuilder();

        if (new File(request.getHeader().getUrl()).isFile()) {
            sb.append("HTTP/1.1 200 OK\n");
        } else{
            sb.append("HTTP/1.1 404 OK\n");
        }
        sb.append("Content-Type: ")
                .append(request.getHeader().getContentType())
                .append("Content-Length: ")
                .append(request.getHeader().getContentLength())
                .append("\n");

        response.setHeader(sb.toString());
        response.setContent(result);

        return response;
    }
}
