/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import players.Client;
import players.HumanStrategy;
import players.Player;
import poker.Game;

/**
 *
 * @author Nickelsilver
 */
public class ConnectionChecker implements Runnable {
    
    private List<Client> clients;

    public ConnectionChecker(Game game) {
        clients = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            clients.add(player.getClient());
        }
    }

    @Override
    public void run() {
        try {
            checkConnections();
        } catch (IOException ex) {
        }
    }

    private void checkConnections() throws IOException {
        while (true) {
            for (Client client : clients) {
                if (!client.isReachable(2000)) {
                    client.setConnected(false);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
    }
    
    
    
}
