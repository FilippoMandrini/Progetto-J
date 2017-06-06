/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import poker.GameObservable;
import server.JSONDecoder;
import server.JSONEncoder;

/**
 *
 * @author Nickelsilver
 */
public class HumanCompleteStrategy extends HumanStrategy{

    private JSONEncoder encoder;
    private JSONDecoder decoder;
    
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
    
    @Override
    public Action act(int minBet, int bet, Set<ActionSet> allowedActions) {
        out.println(encoder.encodeAct(minBet, bet, allowedActions));
        return decoder.decodeAct(in.nextLine());
    }

    @Override
    public void boardUpdated(Board board) {
        out.println(encoder.encodeBoardUpdated(board));
    }

    @Override
    public void playerUpdated(Player player) {
        out.println(encoder.encodePlayerUpdated(player));
    }

    @Override
    public void messageUpdated(String message) {
        out.println(encoder.encodeMessageUpdated(message));
    }

    @Override
    public void handStarted(Player dealer) {
        out.println(encoder.encodeHandStarted(dealer));
    }

    @Override
    public void currentPlayerUpdated(Player currentPlayer) {
        out.println(encoder.encodeCurrentPlayerUpdated(currentPlayer));
    }

    @Override
    public void bettingUpdated(int bet, int minBet, int totalPot) {
        out.println(encoder.encodeBettingUpdated(bet, minBet, totalPot));
    }

    @Override
    public void selfUpdated(Player player) {
        out.println(encoder.encodeSelfUpdated(player));
    }

    @Override
    public void currentPlayerActed(ShadowPlayer shadowCopy) {
        out.println(encoder.encodeCurrentPlayerActed(shadowCopy));
    }

    @Override
    public void gameStarted(List<Player> players, GameType settings) {

    }
    
}
