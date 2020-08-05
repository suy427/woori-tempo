package com.sondahum.woori.tempo.server;

import com.sondahum.woori.tempo.server.packet.WooriHttpHeader;

import java.net.Socket;

public interface SocketListener {
    void run(Socket client, WooriHttpHeader msg);
}
