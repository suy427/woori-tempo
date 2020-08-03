package com.sondahum.woori.tempo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WooriTempoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WooriTempoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WooriTempoApplication.class);
    }
}
