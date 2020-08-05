package com.sondahum.woori.tempo;

import com.sondahum.woori.tempo.server.WooriConfig;
import com.sondahum.woori.tempo.server.WooriServer;
import java.io.IOException;


public class WooriTempo {

    /**
     * USAGE :
     *      ./woori --options [arguments] start
     *      ./woori start
     *
     * options :
     *      --help
     *          display usage.
     *
     *      --port
     *          set server's port.
     *
     *      --basedir
     *          set server base directory.
     *
     *      --indexpage
     *          set index webpage.
     *
     *      --errorpage
     *          set error page.
     *
     */
    public static void main(String[] args) {
        int port = 8080;

        // note. $ ./woori start [port]
        // note. $ ./woori --basedir [path] --
        WooriConfig configure = new WooriConfig();

        if (args.length == 3)
            port = Integer.parseInt(args[2]);
        else if (args.length == 4) {
            configure.setBaseDir(args[2]);
        }



        try {
            WooriServer.getInstance(port, configure);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
