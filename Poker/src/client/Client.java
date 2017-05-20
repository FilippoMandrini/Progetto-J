package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) throws IOException {
        
        Scanner in = null;
        PrintStream out = null;
        Socket socket = null;
        final int port = 7777;
        final String ip = "192.168.1.101";
        String testMessage;
        
        try {
            socket = new Socket(ip, port);
            in = new Scanner(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
            testMessage = in.nextLine();
            System.out.println("Messaggio ricevuto: " + testMessage);
            out.close();
            in.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
        
//        final int port = 7777;
//        final String ip = "192.168.1.101";
//        
//        Socket client = new Socket(ip, port);
//        
//        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
//        Scanner in = new Scanner(client.getInputStream());
//        
//        Scanner tastiera = new Scanner(System.in);
//        while(true) {
//            String input = tastiera.nextLine();
//            out.println(input);
//            String output = in.nextLine();
//            System.out.println(output);
//        }