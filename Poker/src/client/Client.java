package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) throws IOException {
        
        final int port = 7777;
        final String ip = "192.168.1.101";
        
        Socket client = new Socket(ip, port);
        
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        Scanner in = new Scanner(client.getInputStream());
        
        Scanner tastiera = new Scanner(System.in);
        while(true) {
            String input = tastiera.nextLine();
            out.println(input);
            String output = in.nextLine();
            System.out.println(output);
        }
    }
}