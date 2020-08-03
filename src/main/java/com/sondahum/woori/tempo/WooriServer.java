package com.sondahum.woori.tempo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WooriServer extends ServerSocket {

    private static WooriServer instance = null;
    private final List<Socket> clients = new ArrayList<>();
    private ExecutorService receivePool = Executors.newCachedThreadPool();
    private final List<SocketListener> listeners = new ArrayList<>();

    public static WooriServer getInstance() throws IOException {
        if (instance == null) {
            instance = new WooriServer();
        }
        return instance;
    }

    private WooriServer() throws IOException {
        super();
        InetSocketAddress ipep = new InetSocketAddress(9999);
        super.bind(ipep);

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Socket client = super.accept();
                clients.add(client);

                receive(client);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
    }

    private void receive(Socket client) {
        receivePool.execute(() -> {

        });
    }

}
