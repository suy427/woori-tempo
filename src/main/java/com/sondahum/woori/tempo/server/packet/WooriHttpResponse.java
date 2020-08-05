package com.sondahum.woori.tempo.server.packet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WooriHttpResponse {
    String header;
    byte[] content;
}
