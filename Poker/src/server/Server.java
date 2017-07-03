package server;

import gametypes.StandardGame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import json.JSONDecoder;
import json.JSONEncoder;
import players.AIBasicStrategy;
import players.HumanCompleteStrategy;
import players.Player;
import poker.Game;

/**
 * Classe del server
 */
public class Server implements Runnable {

    private BufferedReader in = null;
    private PrintStream out = null;
    private final ServerSocket socket;
    private final int MAX_NO_OF_GAMES = 5;
    private final int ACCEPT_TO;
    private final int READ_TO;
    

    /**
     * Costruttore della classe server
     * @param acceptTO tiemout di attesa massima prima di far partire il gioco
     * @param readTO timeout di attesa massima di un giocatore durante la partita
     * @throws IOException 
     */
    public Server(int acceptTO, int readTO) throws IOException  {
        this.socket = new ServerSocket(7777);
        this.ACCEPT_TO = acceptTO;
        this.READ_TO = readTO;
        this.socket.setSoTimeout(ACCEPT_TO);
    }
    
    /**
     * Lancia in esecuzione il server.
     * Attende la connessione da parte dei client per un certo periodo e 
     * aggiunge gli utenti ad una nuova partita, aggiunge dei bot per riempire 
     * i posti vuoti e inizia la partita
     */
    @Override
    public void run()
    {
        for(int i = 0; i < MAX_NO_OF_GAMES; i++)
        {
            int numClients = 0;
            Socket client;
            Game game = new Game(new StandardGame(5000));
            while (numClients == 0)
            {
                try 
                {
                    while (numClients < game.getSettings().getMaxPlayers()) 
                    {
                        client = socket.accept();
                        client.setSoTimeout(READ_TO);
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
                    if (numClients != 0)
                    {
                        for (int j = 0; j<game.getSettings().getMaxPlayers() - numClients; j++)
                        {

                            Player player = new Player("BOT_" + j, new AIBasicStrategy(85, 5));
                            game.addPlayer(player);
                            game.addObserver(player.getClient());
                        }
                    }
                }
                catch (IOException e)
                {
                    System.err.println("Errore sconosciuto!");
                }
            }
            GameRunnable mainRunnable = new GameRunnable(game);
            Thread mainThread = new Thread(mainRunnable);
            mainThread.start();
            System.out.println("Lanciato Gioco n° " + (i + 1));
        }
    }
}