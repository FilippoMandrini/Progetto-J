/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import gametypes.StandardGame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import players.AIBasicStrategy;
import players.HumanCompleteStrategy;
import players.Player;
import poker.Game;
import server.GameRunnable;
import json.JSONDecoder;
import json.JSONEncoder;

/**
 *
 * @author Nickelsilver
 */
public class ServerTest implements Runnable {

    private BufferedReader in = null;
    private PrintStream out = null;
    private ServerSocket socket;
    private JSONEncoder encoder;
    private JSONDecoder decoder;

    public ServerTest() throws IOException  {
        this.socket = new ServerSocket(7777);
        this.socket.setSoTimeout(20000);
    }
    
    @Override
    public void run()
    {
        int numClients = 0;
        Socket client = null;
        Game game = new Game(new StandardGame(1000));
        try 
        {
            while (true && numClients < game.getSettings().getMaxPlayers()) 
            {
                client = socket.accept();
                client.setSoTimeout(0);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintStream(client.getOutputStream(), true);
                out.println(JSONEncoder.getInstance().encodeGameJoined());
                Player H1 = new Player((String) JSONDecoder.getInstance().decode(in.readLine()), new HumanCompleteStrategy(client));
                game.addPlayer(H1);
                game.addObserver(H1.getClient());
                numClients++;
            }
        } 
        catch (SocketTimeoutException e) 
        {
            for (int i = 0; i<game.getSettings().getMaxPlayers() - numClients; i++)
            {
                Player player = new Player("BOT_" + i, new AIBasicStrategy(85, 5));
                game.addPlayer(player);
                game.addObserver(player.getClient());
            }
        }
        catch (IOException e)
        {
            System.err.println("Errore sconosciuto!");
        }
        GameRunnable mainRunnable = new GameRunnable(game);
        Thread mainThread = new Thread(mainRunnable);
        mainThread.start();
    }
    
    
    public static void main(String[] args) throws IOException {
        Thread server = new Thread(new ServerTest());
        server.start();
    }
}
