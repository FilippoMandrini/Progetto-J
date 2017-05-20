package server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread {
    
    private ServerSocket server;
    final int port = 7777;
    
    public static void main(String[] args) throws IOException, Exception {
        
        new Server();
    }
    
    public Server() throws Exception {
        
        this.server = new ServerSocket(this.port);
        this.start();
    }
    
    public void run() {
        
        while(true) {
            try {
                Socket client = server.accept();
                Connect c = new Connect(client);
            } 
            catch (IOException ex1) {
            }
        }
    }
    
    class Connect extends Thread {
        
        private Socket client = null;
        Scanner in = null;
        PrintStream out = null;
        
        public Connect(Socket clientSocket) throws IOException {
            
            this.client = clientSocket;
            
            try {
                in = new Scanner(new InputStreamReader(client.getInputStream()));
                out = new PrintStream(client.getOutputStream(), true);
            }
            catch (IOException ex2) {
                try {
                    client.close();
                }
                catch(Exception ex3) {
                    System.out.println(ex3.getMessage());
                }
                return;
            }
            this.start();
        }
        
        public void run() {
            try {
                out.flush();
                out.close();
                in.close();
                client.close();
            }
            catch(Exception ex3) {   
            }
        }
    }
}

//        int clientNumber = 0;
//        final int port = 7777;
//        
//        ServerSocket server = new ServerSocket(port);
//        Socket client = server.accept();
//        
//        Scanner in = new Scanner(client.getInputStream());
//        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
//        
//        while(true) {
//            String riga = in.nextLine();
//            out.println("Ricevuto " + riga);
//            out.flush();
//        }
//    }
//    
//    private static class Supporto extends Thread {
//        
//        private Socket socket;
//        private int clientNumber;
//        
//        public Supporto(Socket socket, int clientNumber) {
//            this.socket = socket;
//            this.clientNumber = clientNumber;
//            log("Nuova connessione con client # " + clientNumber + " presso il socket " + socket);
//        }
//    }