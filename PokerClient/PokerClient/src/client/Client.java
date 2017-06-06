
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
        String ip = "localhost";
        String testMessage;
        Player giocatore;

        
        try {
            socket = new Socket(ip, port);
            giocatore = new Player("Giocatore " + socket.toString());
            System.out.println("Creato nuovo Giocatore " + socket.toString());
            in = new Scanner(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
            while (true) {
                if(in.nextLine().equals("inizio"))
                {
                    out.println(giocatore.act());
                    //testMessage = in.nextLine();
                    //System.out.println("Messaggio ricevuto: " + testMessage);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}