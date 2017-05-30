
package pokerclient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private ServerSocket server;
    final int port = 7777;
    int numClients = 0;
    
    public static void main(String[] args) throws IOException, Exception {
        
        new Server();
    }
    
    public Server() throws Exception {
        
        this.server = new ServerSocket(this.port);
        this.run();
    }
    
    public void run() {
       
        System.out.println("Attendo giocatori...");
        Game G1 = new Game();
        while(true && numClients < 2) {
            try {
                Socket client = server.accept();
                this.numClients ++;
                System.out.println("Client numero " + this.numClients + " connesso");
                G1.addServerClient(new ServerClient(client));
            } 
            catch (IOException ex1) {
            }
        }
        System.out.println("Giocatori connessi, inizio il gioco...");
        G1.playGame();
    }
    
}