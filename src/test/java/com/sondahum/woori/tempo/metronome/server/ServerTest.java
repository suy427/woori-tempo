package com.sondahum.woori.tempo.metronome.server;

import com.sondahum.woori.tempo.WooriTempo;
import org.junit.Test;

public class ServerTest {

    @Test
    public void test() {
        String basedir = "C:\\Users\\data\\Desktop\\study-dahum\\git\\mine\\tempo\\src\\test\\resources";
        String[] args = {"./woori","--basedir",basedir, "start"};
        WooriTempo.main(args);
    }
}
