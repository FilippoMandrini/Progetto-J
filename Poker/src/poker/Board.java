package poker;

import exceptions.InvalidPlayerNameException;
import exceptions.NotEnoughCardsException;
import exceptions.PlayerNotFoundException;
import handtypes.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Classe che rappresenta il banco
 */
public class Board {

    private Deck mazzo;
    private ArrayList<Player> giocatori;
    private boolean hasPlayers;
    private ArrayList<Card> communityCards;
    private PlayerEvaluator evaluator;

    /**
     * Esegue una partita di Poker
     */
    public void playGame() {
        preflop();
        flop();
        turn();
        river();
        for (Player player : giocatori) {
            player.setCurrent(evaluator.evaluate(player, communityCards));
            //System.out.println(player.getName() + " ha in mano " + player.getCurrent());
        }
        getRanking();
    }

    /**
     * Esegue il reset delle carte del banco e del mazzo terminando la mano 
     * @return 
     */
    public boolean clear() {
        this.communityCards.clear();
        for (Player player : giocatori) {
            giocatori.add(player);
        }
        this.mazzo.restore();
        return true;
    }

    /**
     * Distribuisce le due carte personali del giocatore
     * @param player il giocatore
     * @return
     */
    public boolean dealCards(Player player) {
        player.clearCards();
        for (int i = 0; i < 2; i++) {
            player.addCard(this.mazzo.getCard());
        }
        return true;
    }

    /**
     * Distribuisce le due carte personali di tutti i giocatori
     * e imposta lo stake iniziale a 2000 
     */
    public void preflop() {
        for (Player player : giocatori) {
            dealCards(player);
        }
    }

    /**
     * Mostra le prime tre carte comuni
     * @return
     */
    public boolean flop() {
        for (int i = 0; i < 3; i++) {
            communityCards.add(mazzo.getCard());
        }
        return true;
    }

    /**
     * Mostra la quarta carta comune
     * @return
     */
    public boolean turn() {
        mazzo.burnCard();
        communityCards.add(mazzo.getCard());
        return true;
    }

    /**
     * Mostra la quinta e ultima carta comune
     * @return
     */
    public boolean river() {
        mazzo.burnCard();
        communityCards.add(mazzo.getCard());
        return true;
    }
        
    /**
     * Stampa la lista dei vincitori
     * @return 
     */
    public ArrayList<Player> getRanking() {
        ArrayList<Player> ranking = new ArrayList<>();
        ranking.addAll(giocatori);
        Collections.sort(ranking);
        return ranking;
    }

    /**
     * Ritorna se sono o no presenti i giocatori
     * @return
     */
    public boolean hasPlayers() {
        return hasPlayers;
    }

    /**
     * Aggiunge un giocatore controllando che non si utilizzi un nome 
     * già scelto da un altro utente
     * @param player il giocatore
     * @return
     */
    public boolean addPlayer(Player player) {
        boolean presence = false;
        for (Player giocatore : giocatori) {
            if (giocatore.getName().equals(player.getName())) {
                presence = true;
            }
        }
        if (presence == false) {
            giocatori.add(player);
            player.setStake(2000);
        } else {
            throw new InvalidPlayerNameException("Nome già utilizzato!");
        }
        return giocatori.contains(player);
    }

    /**
     * Ritorna la mano del giocatore 
     * @param player il giocatore
     * @return la mano del giocatore
     */
    public Hand getPlayerHand(Player player) {
        if (giocatori.contains(player)) {
            return player.getCurrent();
        }
        throw new PlayerNotFoundException("Giocatore non trovato!");
    }

    public Board() {
        this.mazzo = new Deck();
        this.giocatori = new ArrayList<>();
        this.hasPlayers = false;
        this.communityCards = new ArrayList<>();
        this.evaluator = new PlayerEvaluator();
        
    }

    public ArrayList<Player> getGiocatori() {
        return giocatori;
    }
}