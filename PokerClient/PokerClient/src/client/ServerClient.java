
package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerClient {

    public Socket client = null;
    public Scanner in = null;
    public PrintStream out = null;

    public ServerClient(Socket clientSocket) throws IOException {

        this.client = clientSocket;

        try {
            in = new Scanner(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);
        } catch (IOException ex2) {
            return;
        }
    }
    
    public String act()
    {
        try {
            String messaggio = in.nextLine();
            return messaggio;
            
        } catch (Exception ex3) {
        }
        return "ERRORE";
    }
}
