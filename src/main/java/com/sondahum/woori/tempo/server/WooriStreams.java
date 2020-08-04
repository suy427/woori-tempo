package com.sondahum.woori.tempo.server;

import java.io.*;
import java.net.Socket;

public class WooriStreams {
    private PrintWriter toClient;
    private BufferedReader fromClient;

    public WooriStreams(Socket client) throws IOException {
        toClient = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public WooriHttp readRequest() throws IOException {
        String[] firstLine = fromClient.readLine().split(" ");
        WooriHttp request = new WooriHttp();

        request.setMethod(firstLine[0]);
        request.setUrl(firstLine[1]);

        String line = null;
        while((line = fromClient.readLine()) != null) {
            if (line.equals("")) break;
            parseMessage(request, line);
        }

        return request;
    }

    private void parseMessage(WooriHttp request, String line) {
        String[] headerArray = line.split(" ");
        if (headerArray[0].startsWith("Host:")) {
            request.setHost(headerArray[1].trim());
        } else if (headerArray[0].startsWith("Content-Length:")) {
            int length = Integer.parseInt(headerArray[1].trim());
            request.setContentLength(length);
        } else if (headerArray[0].startsWith("User-Agent:")) {
            request.setUserAgent(line.substring(12));
        } else if (headerArray[0].startsWith("Content-Type:")) {
            request.setContentType(headerArray[1].trim());
        }
    }

    public void sendResponse(byte[] response) {
        toClient.print(response);
    }

    public String createResponse(WooriHttp request) {
        StringBuilder sb = new StringBuilder();

        if (new File(request.getUrl()).isFile()) {
            sb.append("HTTP/1.1 200 OK\n");
        } else{
            sb.append("HTTP/1.1 404 OK\n");
        }
        sb.append("Content-Type: ")
                .append(request.getContentType())
                .append("Content-Length: ")
                .append(request.getContentLength())
                .append("\n");

        flushOutputStream();
        return sb.toString();
    }

    private void flushOutputStream() {
        toClient.flush();
    }

}
