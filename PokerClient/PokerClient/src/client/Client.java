/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Nickelsilver
 */
public class Client implements Runnable {

    public Socket server;
    public BufferedReader in = null;
    public PrintStream out = null;
    final int port = 7777;
    String ip = "localhost";
    public boolean disconnected = false;
    private final String nickname;

    public Client(Socket server, String nickname) {
        this.nickname = nickname;
        this.server = server;
    }
    
    @Override
    public void run() 
    {
        Game game = new Game();
        MainGUI mainGui = new MainGUI(game);
        mainGui.pack();
        mainGui.setResizable(false);
        mainGui.setLocationRelativeTo(null);
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
