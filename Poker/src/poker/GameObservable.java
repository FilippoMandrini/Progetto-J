package poker;

import gametypes.GameType;
import java.util.*;
import players.Player;

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
            observer.handStarted(dealer.getShadowCopy(), dealerPosition);
        }
    }

    /**
     * Segnala quando viene eseguita una puntata
     * @param bet l'ammontare della puntata
     * @param minBet l'ammontare minimo della puntata
     * @param totalPot il totale delle scommesse nel pot
     */
    public void notifyBettingUpdated(int bet, int minBet, int totalPot) {
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
            observer.currentPlayerUpdated(currentPlayer.getShadowCopy(), currentPlayerPosition);
        }
    }

    /**
     * Segnala quando il giocatore attuale compie un'azione
     * @param currentPlayer il giocatore attuale
     */
    public void notifyCurrentPlayerActed(Player currentPlayer) {
        for (GameObserver observer : observers) {
            observer.currentPlayerActed(currentPlayer.getShadowCopy());
        }
    }

    /**
     * Manda agli altri giocatore le informazioni permesse su se stesso
     * @param players il giocatore
     */
    public void notifyHiddenPlayersUpdated(List<Player> players) {
        for (Player notifyPlayer : players) {
            if (notifyPlayer.getClient().isConnected()) {
                for (Player player : players) {
                    if (!notifyPlayer.equals(player)) {
                        notifyPlayer.getClient().playerUpdated(player.getShadowCopy());
                    } else {
                        notifyPlayer.getClient().selfUpdated(player);
                    }
                }
            }
        }
    }

    /**
     * Segnala quando i giocatori subiscono modifiche
     * @param players i giocatori
     */
    public void notifyPlayersUpdated(List<Player> players) {
        for (GameObserver observer : observers) {
            for (Player player : players) {
                observer.playerUpdated(player);
            }
        }
    }
    
    public void notifyDisconnect() {
        for (GameObserver observer : observers) {
            observer.disconnect();          
        }
    }

}
