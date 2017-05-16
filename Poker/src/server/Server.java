package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
   
public class Server {
    
    public static void main(String[] args) throws IOException {
        
        final int port = 7777;
        
        ServerSocket server = new ServerSocket(port);
        Socket client = server.accept();
        
        Scanner in = new Scanner(client.getInputStream());
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        
        while(true) {
            String riga = in.nextLine();
            out.println("Ricevuto " + riga);
            out.flush();
        }
    }
}