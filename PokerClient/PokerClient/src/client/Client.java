package client;

import gui.MainGUI;
import json.JSONDecoder;
import json.JSONEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import model.Game;

/**
 * Classe per il Client
 */
public class Client implements Runnable {

    public Socket server;
    public BufferedReader in = null;
    public PrintStream out = null;
    final int port = 7777;
    String ip = "localhost";
    public boolean disconnected = false;
    private final String nickname;

    /**
     * Costruttore del Client
     * @param server la socket del server
     * @param nickname il nickname del giocatore
     */
    public Client(Socket server, String nickname) {
        this.nickname = nickname;
        this.server = server;
    }
    
    /**
     * Lancia il Client che si connette al Server
     * Visualizza la GUI per permettere all'utente di giocare
     * una partita
     */
    @Override
    public void run() 
    {
        Game game = new Game();
        MainGUI mainGui = new MainGUI(game);
        mainGui.pack();
        mainGui.setLocationRelativeTo(null);
        mainGui.setResizable(false);
        mainGui.setVisible(true);    
        game.addObserver(mainGui);
        try 
        {
            //server = new Socket(ip, port);
            System.out.println(server);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new PrintStream(server.getOutputStream(), true);
        } 
        catch (IOException ex) 
        {
            System.out.println("Errore Sconosciuto!");
        }
        //TextUI ui = new TextUI(game);
        //game.addObserver(ui);
        try
        {
            Sender.init(server);
        }
        catch (NullPointerException ex)
        {
            System.err.println("Errore nell'instanziare il sender");
        }
        try 
        {
            String toDecode = in.readLine();
            if(JSONDecoder.getInstance(game).decode(toDecode) != null)
            {
                Sender.getInstance().sendRaw(JSONEncoder.getInstance().encodeGameJoined(nickname));
            }
        } 
        catch (IOException | NullPointerException ex) 
        {
            disconnected = true;
        }
        while (true && !disconnected)
        {
            try 
            {
                String toDecode = in.readLine();
                JSONDecoder.getInstance(game).decode(toDecode);              
            } 
            catch (IOException | NullPointerException ex)
            {
                System.err.println("CONNESSIONE CON IL SERVER PERSA");
                disconnected = true;
            }
        }
        System.err.println("PROGRAMMA TERMINATO");
    }
}
