package com.sondahum.woori.tempo.server;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WooriHttp {
    private String method; //GET, POST, PUT, DELETE ....
    private String url;
    private String host; // host
    private int contentLength; //COntent-Length : body 길이
    private String userAgent; // User-Agent : 브라우저 정보
    private String contentType; // Content-Type : 사용자가 요청한 컨텐츠의 타입


    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", host='" + host + '\'' +
                ", contentLength='" + contentLength + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
