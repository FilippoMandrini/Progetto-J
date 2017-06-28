
package client;

import json.JSONEncoder;
import actions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Set;

public class Sender {
 
    private static Sender instance;
    private BufferedReader in = null;
    private PrintStream out = null;
    private Socket server;
    private boolean blocked = false;
    
    private Sender(Socket server) {

        this.server = server;

        try {
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new PrintStream(server.getOutputStream(), true);
        } catch (IOException ex2) {
            System.err.println("Errore di apertura stream di comunicazione!");
        }
    }
    public void sendRaw(String data)
    {
        blocked = true;
        out.println(data);
        blocked = false;
    }
    
    public void sendAction(Action action)
    {
        sendRaw(JSONEncoder.getInstance().encodeAct(action));
    }

    public static synchronized Sender getInstance()
    {
        return instance;
    }

    public boolean isBlocked() {
        return blocked;
    }
    
    
    
    public static void init(Socket server)
    {
        if(instance == null)
        {
            instance = new Sender(server);
        }
    }
}
