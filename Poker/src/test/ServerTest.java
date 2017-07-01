package test;

import java.io.IOException;
import server.Server;

/**
 * Classe di test del server
 */
public class ServerTest {
    
    public static void main(String[] args) throws IOException {
        Thread server = new Thread(new Server(10000, 30000));
        server.start();
    }
}
