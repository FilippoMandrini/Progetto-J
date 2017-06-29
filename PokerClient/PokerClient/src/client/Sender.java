package client;

import json.JSONEncoder;
import actions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Classe Sender per gestire le comunicazioni
 */
public class Sender {
 
    private static Sender instance;
    private BufferedReader in = null;
    private PrintStream out = null;
    private Socket server;
    private boolean blocked = false;
    
    /**
     * Costruttore del Sender
     * @param server la socket del server
     */
    private Sender(Socket server) {

        this.server = server;

        try {
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new PrintStream(server.getOutputStream(), true);
        } catch (IOException ex2) {
            System.err.println("Errore di apertura stream di comunicazione!");
        }
    }
    
    /**
     * Invia una stringa di dati
     * @param data la stringa di dati
     */
    public void sendRaw(String data)
    {
        blocked = true;
        out.println(data);
        blocked = false;
    }
    
    /**
     * Invia un'azione codificata da JSON
     * @param action l'azione da codificare e inviare
     */
    public void sendAction(Action action)
    {
        sendRaw(JSONEncoder.getInstance().encodeAct(action));
    }

    /**
     * Ritorna l'istanza del sender
     * @return l'istanza del sender
     */
    public static synchronized Sender getInstance()
    {
        return instance;
    }

    /**
     * Indica se è bloccato o no
     * @return true se è bloccato, false altrimenti
     */
    public boolean isBlocked() {
        return blocked;
    }
    
    /**
     * Istanzia il sender
     * @param server la socket del server
     */
    public static void init(Socket server)
    {
        if(instance == null)
        {
            instance = new Sender(server);
        }
    }
}
