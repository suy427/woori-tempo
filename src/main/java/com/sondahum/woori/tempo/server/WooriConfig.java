package com.sondahum.woori.tempo.server;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WooriConfig {
    private String baseDir = "/tmp/woori";
    private String fileName = "/";

    public void setBaseDir(String path) {
        baseDir = path;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
