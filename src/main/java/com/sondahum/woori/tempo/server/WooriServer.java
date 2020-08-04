package com.sondahum.woori.tempo.server;

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
    private final FrontController controller;


    public static WooriServer getInstance(int port, WooriConfig configure) throws IOException {
        if (instance == null) {
            instance = new WooriServer(port, configure);
        }
        return instance;
    }

    private WooriServer(int port, WooriConfig configure) throws IOException {
        super();
        this.controller = new FrontController(configure);
        // note. ipep ==> IP EndPoint.
        InetSocketAddress ipep = new InetSocketAddress(port);
        super.bind(ipep);

        // note. 일단 client를 기다리는 상태 listen상태로 대기함.
        // note. 그리고 client가 접속하면 thread하나 만들어서 처리함.
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Socket client = super.accept();
                System.out.println("Client" + client.getInetAddress().getHostAddress() + "visited.");
                clients.add(client);

                handleClient(client); // note. client가 들어오면 receive로 handling해줌.
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
    }

    private void handleClient(Socket client) throws IOException {
        WooriStreams streams = new WooriStreams(client);

        WooriHttp request = streams.readRequest();
        System.out.println("received HTTP Message :\n" + request);

        byte[] response = controller.requestMapping(request);
        streams.sendResponse(response);

        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int readCount = 0;
        while ((readCount = fis.read(buffer)) != -1) {
            out.write(buffer, 0, readCount);
        }
        out.flush();


        out.close();
        in.close();
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
        ByteBuffer length = ByteBuffer.allocate(4);
        length.putInt(data.length);
        try (OutputStream sender = client.getOutputStream()) {
            sender.write(length.array());
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
