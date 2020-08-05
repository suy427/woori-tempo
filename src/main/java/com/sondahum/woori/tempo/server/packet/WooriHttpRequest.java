package com.sondahum.woori.tempo.server.packet;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WooriHttpRequest {
    WooriHttpHeader header;
    String body;

    public byte[] getBody() {
        return body.getBytes();
    }
}
