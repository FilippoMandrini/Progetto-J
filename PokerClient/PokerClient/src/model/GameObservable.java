package model;

import actions.ActionSet;
import gametypes.GameType;
import java.util.*;

/**
 * Classe astratta Osservabile del game 
 */
public abstract class GameObservable {

    protected final Set<GameObserver> observers = new HashSet<>();

    /**
     * Conta gli osservatori
     * @return il numero di osservatori
     */
    public synchronized int countObservers() {
        return observers.size();
    }

    /**
     * Cancella un osservatore
     * @param observer l'osservatore
     */
    public synchronized void deleteObserver(GameObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    /**
     * Aggiunge un osservatore
     * @param observer l'osservatore
     */
    public synchronized void addObserver(GameObserver observer) {
        observers.add(observer);

    }

    /**
     * Segnala un aggiornamento del banco
     * @param board il banco
     */
    public void notifyBoardUpdated(Board board) {
        for (GameObserver observer : observers) {
            observer.boardUpdated(board);
        }
    }

    /**
     * Segnala quando viene inviato un messaggio al client
     * @param message il client
     */
    public void notifyMessageUpdated(String message) {
        for (GameObserver observer : observers) {
            observer.messageUpdated(message);
        }
    }


    /**
     * Segnala quando inizia il gioco
     * @param players la lista dei giocatori
     * @param settings le impostazioni
     */
    public synchronized void notifyGameStarted(List<Player> players, GameType settings) {
        for (GameObserver observer : observers) {
            observer.gameStarted(players, settings);

        }
    }

    /**
     * Segnala quando inizia la mano
     * @param dealer il dealer della mano
     */
    public void notifyHandStarted(Player dealer, int dealerPosition) {
        for (GameObserver observer : observers) {
            observer.handStarted(dealer, dealerPosition);
        }
    }

    /**
     * Segnala quando viene eseguita una puntata
     * @param bet l'ammontare della puntata
     * @param minBet l'ammontare minimo della puntata
     * @param totalPot il totale delle scommesse nel pot
     */
    public void notifyBettingUpdate(int bet, int minBet, int totalPot) {
        for (GameObserver observer : observers) {
            observer.bettingUpdated(bet, minBet, totalPot);
        }
    }

    /**
     * Segnala quando il giocatore attuale subisce una modifica
     * @param currentPlayer il giocatore attuale
     * @param currentPlayerPosition posizione attuale del giocatore
     */
    public void notifyCurrentPlayerUpdated(Player currentPlayer, int currentPlayerPosition) {
        for (GameObserver observer : observers) {
            observer.currentPlayerUpdated(currentPlayer, currentPlayerPosition);
        }
    }

    /**
     * Segnala quando il giocatore attuale compie un'azione
     * @param currentPlayer il giocatore attuale
     */
    public void notifyCurrentPlayerActed(Player currentPlayer) {
        for (GameObserver observer : observers) {
            observer.currentPlayerActed(currentPlayer);
        }
    }

    /**
     * Segnala quando i giocatori subiscono modifiche
     * @param players i giocatori
     */
    public void notifyPlayersUpdated(List<Player> players) {
        for (GameObserver observer : observers) {
            for (Player player : players) {
                if (player.getCards().isEmpty() || player.getCards() == null) {
                    observer.playerUpdated(player, false);
                } else {
                    observer.playerUpdated(player, true);
                }
            }
        }
    }
    
    public void notifyPlayerUpdated(Player player) {
        for (GameObserver observer : observers) {
            if (player.getCards() == null || player.getCards().isEmpty()) {
                observer.playerUpdated(player, false);
            } else {
                observer.playerUpdated(player, true);
            }
        }
    }
    
    public void notifyActionRequest(int bet, int minBet, Set<ActionSet> allowedActions) {
        for (GameObserver observer : observers) 
        {
                observer.actionRequest(bet, minBet, allowedActions);
        }
    }
    
    public void notifyDisconnect() {
        for (GameObserver observer : observers) 
        {
                observer.disconnect();
        }
    }    
    

}
