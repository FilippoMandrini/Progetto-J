package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import players.Client;
import players.Player;
import poker.Game;

/**
 * Classe per il controllo delle connessioni
 */
public class ConnectionChecker implements Runnable {
    
    private boolean isGameRunning;
    private List<Client> clients;

    /**
     * Costruttore della classe ConnectionChecker
     * @param game la partita
     */
    public ConnectionChecker(Game game) 
    {
        isGameRunning = true;
        clients = new ArrayList<>();
        for (Player player : game.getPlayers()) 
        {
            clients.add(player.getClient());
        }
    }

    /**
     * Lancia il controllo delle connessioni
     */
    @Override
    public void run() 
    {
        checkConnections();
    }

    /**
     * Controlla lo stato delle connessioni dei clients
     */
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
                Thread.sleep(2000);
            } 
            catch (InterruptedException ex) {
            }
        }
    }
    
    /**
     * Prepara la lista dei clients connessi
     */
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

    /**
     * Segnala se la partita è in corso
     * @return true se la partita è in esecuzione, false altrimenti
     */
    public boolean isIsGameRunning() {
        return isGameRunning;
    }

    /**
     * Imposta se la partita è in corso
     * @param isGameRunning true se la partita è in esecuzione, false altrimenti
     */
    public void setIsGameRunning(boolean isGameRunning) {
        this.isGameRunning = isGameRunning;
    }
    
}