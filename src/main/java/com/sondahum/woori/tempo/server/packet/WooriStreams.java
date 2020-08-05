package com.sondahum.woori.tempo.server.packet;

import java.io.*;
import java.net.Socket;

public class WooriStreams {
    private PrintWriter toClient;
    private BufferedReader fromClient;
    private OutputStream out;
    private InputStream in;

    public WooriStreams(Socket client) throws IOException {
        out = client.getOutputStream();
        in = client.getInputStream();
        toClient = new PrintWriter(new OutputStreamWriter(out));
        fromClient = new BufferedReader(new InputStreamReader(in));
    }

    public WooriHttpRequest readRequest() throws IOException {
        String[] firstLine = fromClient.readLine().split(" ");
        WooriHttpHeader header = new WooriHttpHeader();
        WooriHttpRequest request = new WooriHttpRequest();

        header.setMethod(firstLine[0]);
        header.setUrl(firstLine[1]);
        // todo GET, POST 구분
        String line = null;
        while((line = fromClient.readLine()) != null) {
            if (line.equals("")) break;
            parseHeader(header, line);
        }

//        StringBuilder requestBody = new StringBuilder();
//        while((line = fromClient.readLine()) != null) {
//            if (line.equals("")) break;
//            requestBody.append(line);
//        }
//        request.setBody(requestBody.toString());
        request.setHeader(header);

        return request;
    }

    private void parseHeader(WooriHttpHeader request, String line) {
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

    public void sendResponse(WooriHttpResponse response) throws IOException {
        toClient.print(response.header);
        toClient.flush();

        out.write(response.content);
    }
    public void close() throws IOException {
        toClient.flush();
        toClient.close();
        fromClient.close();
    }

}
