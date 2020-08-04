package com.sondahum.woori.tempo.server;

import java.net.Socket;

public interface SocketListener {
    void run(Socket client, WooriHttp msg);
}
