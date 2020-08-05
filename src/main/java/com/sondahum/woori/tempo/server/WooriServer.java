package com.sondahum.woori.tempo.server;

import com.sondahum.woori.tempo.server.controller.FrontController;
import com.sondahum.woori.tempo.server.packet.WooriHttpHeader;
import com.sondahum.woori.tempo.server.packet.WooriStreams;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WooriServer extends ServerSocket {
    private static WooriServer instance = null;
    private final List<Socket> clients = new ArrayList<>();
    private ExecutorService receivePool = Executors.newCachedThreadPool();
    private final List<SocketListener> listeners = new ArrayList<>();
    private final FrontController frontController;


    public static WooriServer getInstance(int port, WooriConfig configure) throws IOException {
        if (instance == null) {
            instance = new WooriServer(port, configure);
        }
        return instance;
    }

    private WooriServer(int port, WooriConfig configure) throws IOException {
        super();
        this.frontController = new FrontController(configure);
        // note. ipep ==> IP EndPoint.
        InetSocketAddress ipep = new InetSocketAddress(port);
        super.bind(ipep);

        // note. 일단 client를 기다리는 상태 -> listen상태로 대기함.
        // note. 그리고 client가 접속하면 thread하나 만들어서 처리함.
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Socket client = super.accept();
                System.out.println("Client" + client.getInetAddress().getHostAddress() + "visited.");
                clients.add(client);

                handleClient(client); // note. 여기가 request를 처리하는 부분.
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
    }

    private void handleClient(Socket client) throws IOException {
        WooriStreams streams = new WooriStreams(client);

        WooriHttpHeader request = streams.readRequest();
        System.out.println("received HTTP Message :\n" + request);

        byte[] response = frontController.controllerMapping(request);
        streams.sendResponse(response); // note. client에게 response보냄.

        streams.close();
        client.close(); // 클라이언트와 접속이 close된다.
    }

    public void addListener(SocketListener listener) {
        listeners.add(listener);
    }

    public void send(String msg) {
        for (Socket client : clients) {
            send(client, msg);
        }
    }

    public void send(Socket client, String msg) {
        byte[] data = msg.getBytes();

        try (OutputStream sender = client.getOutputStream()) {
            sender.write(data);
        } catch (Throwable e) {
            try {
                client.close();
            } catch (IOException x) {
                x.printStackTrace();
            }
            clients.remove(client);
        }
    }
}
