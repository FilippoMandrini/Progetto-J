/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import players.Client;
import players.Player;
import poker.Game;

/**
 *
 * @author Nickelsilver
 */
public class ConnectionChecker implements Runnable {
    
    private boolean isGameRunning;
    private List<Client> clients;

    public ConnectionChecker(Game game) 
    {
        isGameRunning = true;
        clients = new ArrayList<>();
        for (Player player : game.getPlayers()) 
        {
            clients.add(player.getClient());
        }
    }

    @Override
    public void run() 
    {
        checkConnections();
    }

    private void checkConnections() 
    {
        while (isGameRunning) 
        {        
            updateClients();
            for (int i = 0; i<clients.size(); i++) 
            {
                if (!clients.get(i).isBlocked())
                {
                    try 
                    {
                        clients.get(i).ping();
                    } 
                    catch (IOException ex) 
                    {
                        System.out.println("[DISCONNESSIONE] " + ex.getMessage());
                        clients.get(i).setConnected(false);
                    }
                }
            }
            try 
            {
                Thread.sleep(1000);
            } 
            catch (InterruptedException ex) {
            }
        }
    }
    
    public void updateClients()
    {
        ArrayList<Client> connected = new ArrayList<>();
        for(Iterator<Client> iter = clients.iterator(); iter.hasNext();)
        {
            Client client = iter.next();
            if (client.isConnected())
            {
                connected.add(client);
            }
        }
        clients = connected;
    }

    public boolean isIsGameRunning() {
        return isGameRunning;
    }

    public void setIsGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }
    
    
}
