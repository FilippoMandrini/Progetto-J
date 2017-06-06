package players;

import actions.Action;
import actions.ActionSet;
import gametypes.GameType;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import poker.Board;
import server.JSONDecoder;
import server.JSONEncoder;

/**
 *Classe che si occupa della comunicazione con il client e stabilire le mosse del giocatore
 */
public class HumanCompleteStrategy extends HumanStrategy{

    private JSONEncoder encoder;
    private JSONDecoder decoder;
    
    /**
     * Costruttore della classe
     * @param socket il socket del client
     */
    public HumanCompleteStrategy(Socket socket) {
        super(socket);
         try {
            in = new Scanner(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
        } catch (IOException ex) {
        }   
        this.encoder = JSONEncoder.getInstance();
        this.decoder = JSONDecoder.getInstance();
    }
    
    /** {@inheritDoc} */
    @Override
    public Action act(int minBet, int bet, Set<ActionSet> allowedActions) {
        out.println(encoder.encodeAct(minBet, bet, allowedActions));
        return decoder.decodeAct(in.nextLine());
    }
    
    /** {@inheritDoc} */
    @Override
    public void boardUpdated(Board board) {
        out.println(encoder.encodeBoardUpdated(board));
    }
    
    /** {@inheritDoc} */
    @Override
    public void playerUpdated(Player player) {
        out.println(encoder.encodePlayerUpdated(player));
    }
    
    /** {@inheritDoc} */
    @Override
    public void messageUpdated(String message) {
        out.println(encoder.encodeMessageUpdated(message));
    }
    
    /** {@inheritDoc} */
    @Override
    public void handStarted(Player dealer) {
        out.println(encoder.encodeHandStarted(dealer));
    }
    
    /** {@inheritDoc} */
    @Override
    public void currentPlayerUpdated(Player currentPlayer) {
        out.println(encoder.encodeCurrentPlayerUpdated(currentPlayer));
    }
    
    /** {@inheritDoc} */
    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {
        out.println(encoder.encodeBettingUpdated(bet, minBet, totalPot));
    }
    
    /** {@inheritDoc} */
    @Override
    public void selfUpdated(Player player) {
        out.println(encoder.encodeSelfUpdated(player));
    }
    
    /** {@inheritDoc} */
    @Override
    public void currentPlayerActed(ShadowPlayer shadowCopy) {
        out.println(encoder.encodeCurrentPlayerActed(shadowCopy));
    }
    
    /** {@inheritDoc} */
    @Override
    public void gameStarted(List<Player> players, GameType settings) {

    }
    
}
