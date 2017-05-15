package poker;

import exceptions.InvalidPlayerNameException;
import exceptions.NotEnoughCardsException;
import exceptions.PlayerNotFoundException;
import handtypes.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *  Classe che rappresenta il banco
 */
public class Board {

    private Deck mazzo;
    private ArrayList<Player> giocatori;
    private boolean hasPlayers;
    private ArrayList<Card> communityCards;

    /**
     * Esegue una partita di Poker
     */
    public void playGame() {
        preflop();
        flop();
        turn();
        river();
        for (Player player : giocatori) {
            evaluatePlayer(player);
            System.out.println(player.getName() + " ha in mano " + player.getCurrent());
        }
        getWinners();
    }

    /**
     * Esegue il reset delle carte del banco e del mazzo terminando la mano 
     * @return 
     */
    public boolean clearBoard() {
        this.communityCards.clear();
        for (Player player : giocatori) {
            giocatori.add(player);
        }
        this.mazzo.restore();
        return true;
    }

    /**
     * Termina la partita di Poker
     * @return
     */
    public boolean terminateBoard() {
        clearBoard();
        this.giocatori.clear();
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
//            player.setStake(2000);
            dealCards(player);
//            ranking.get(0).setBottone(true);
//            ranking.get(1).setSmallblind(true);
//            ranking.get(2).setBigblind(true);
//            betBlinds();
            
            
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
    
    public void betBlinds() {
        for(Player player : giocatori) {
            if(player.getSmallblind()) {
                player.setStake(player.getStake() - 10);
            }
            if(player.getBigblind()) {
                player.setStake(player.getStake() - 20);
            }
        }
    }
    
        
    /**
     * Stampa la lista dei vincitori
     */
    public void getWinners() {
        ArrayList<Player> ranking = new ArrayList<>();
        ArrayList<Player> winners = new ArrayList<>();
        ranking.addAll(giocatori);
        Collections.sort(ranking);
        double bestPoints = ranking.get(0).getHandPoints();
        for (int i = 0; i < ranking.size(); i++) {
            if (ranking.get(i).getHandPoints() == bestPoints) {
                winners.add(ranking.get(i));
            }
        }
        System.out.println("Vincitori:");
        for (int i = 0; i < winners.size(); i++) {
            System.out.println(winners.get(i).getName() + " con " + winners.get(i).getCurrent());
            //Metodo da invocare che consegna fiches vinte dai vincitori es. winners.get(i).obtainFiches(stake/winners.size());
        }
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

    /**
     * Valuta la miglior mano del giocatore
     * @param player il giocatore
     */
    public void evaluatePlayer(Player player) {
        if (!giocatori.contains(player)) {
            throw new PlayerNotFoundException("Giocatore non trovato!");
        }
        ArrayList<Card> toEvaluate = new ArrayList<>();
        toEvaluate.addAll(player.getPlayerCards());
        toEvaluate.addAll(this.communityCards);
        player.setCurrent(evaluateFull(toEvaluate));
    }

    /**
     * Restituisce la combinazione migliore tra tutte le combinazioni di carte
     *
     * @param cards le carte del giocatore e le carte del tavolo
     * @return la mano migliore del giocatore
     * @throws NotEnoughCardsException
     */
    @SuppressWarnings("empty-statement")
    public Hand evaluateFull(ArrayList<Card> cards) throws NotEnoughCardsException {
        if (cards.size() != 7) {
            throw new NotEnoughCardsException("Carte in numero errato");
        }
        ArrayList<Hand> results = new ArrayList<>();
        int k = 5;
        int indices[] = new int[k];
        if (k <= cards.size()) {
            for (int i = 0; i < k; i++) {
                indices[i] = i;
            }
            results.add(evaluateSingle(getSubset(cards, indices)));
            while (true) {
                int i;
                // find position of item that can be incremented
                for (i = k - 1; i >= 0 && indices[i] == cards.size() - k + i; i--);
                if (i < 0) {
                    break;
                }
                indices[i]++;
                for (++i; i < k; i++) {
                    indices[i] = indices[i - 1] + 1;
                }
                results.add(evaluateSingle(getSubset(cards, indices)));
            }
        }
        Collections.sort(results);
        return results.get(0);
    }
    
    /**
    * Ricava un sottogruppo di cinque carte dalle 7 iniziali
    * @param input la lista delle carte personali e comuni
    * @param subset indici delle carte di cui fare un sottogruppo di cinque carte
    * @return le cinque carte associate agli indici
    */
    private ArrayList<Card> getSubset(List<Card> input, int[] subset) {
        ArrayList<Card> toHand = new ArrayList(subset.length);
        toHand.clear();
        for (int i = 0; i < subset.length; i++) {
            toHand.add(input.get(subset[i]));
        }
        return toHand;
    }

    /**
     * Determina il punteggio di una singola mano
     *
     * @param cards un gruppo di cinque carte
     * @return il punteggio per questo specifico gruppo di carte
     * @throws NotEnoughCardsException
     */
    public Hand evaluateSingle(ArrayList<Card> cards) throws NotEnoughCardsException {
        if (cards.size() != 5) {
            throw new NotEnoughCardsException("Carte in numero errato");
        }
        Collections.sort(cards);
        ArrayList<Card> sortedCards = new ArrayList<>();
        int secondMaxIndex = 0;
        int maxIndex = 0;
        int max = 0;
        int[] cardCounter = new int[13];
        for (Card carta : cards) {
            cardCounter[carta.getValue()]++;
        }
        for (int i = cardCounter.length - 1; i >= 0; i--) {
            if (cardCounter[i] > max) {
                max = cardCounter[i];
                maxIndex = i;
            }
        }
        cardCounter[maxIndex] = 0;
        int secondMax = 0;
        for (int i = cardCounter.length - 1; i >= 0; i--) {
            if (cardCounter[i] > secondMax) {
                secondMax = cardCounter[i];
                secondMaxIndex = i;
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getValue() == maxIndex) {
                sortedCards.add(cards.get(i));
                cards.remove(cards.get(i));
                i--;
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getValue() == secondMaxIndex) {
                sortedCards.add(cards.get(i));
                cards.remove(cards.get(i));
                i--;
            }
        }
        sortedCards.addAll(cards);
        cards.clear();
        if (checkColore(sortedCards) && checkScala(sortedCards)) {
            return new ScalaColore(sortedCards);
        }
        if (max == 4) {
            return new PokerHand(sortedCards);
        }
        if (max == 3) {
            if (secondMax == 2) {
                return new Full(sortedCards);
            }
        }
        if (checkColore(sortedCards)) {
            return new Colore(sortedCards);
        }
        if (checkScala(sortedCards)) {
            return new Scala(sortedCards);
        }
        if (max == 3) {
            if (secondMax != 2) {
                return new Tris(sortedCards);
            }
        }
        if (max == 2) {
            if (secondMax == 2) {
                return new DoppiaCoppia(sortedCards);
            } else {
                return new Coppia(sortedCards);
            }
        }
        if (max == 1) {
            return new CartaAlta(sortedCards);
        }
        return null;
    }
    
    /**
    * Controlla se la mano costituisce la combinazione "Colore"
    * @param cards le carte della mano
    * @return una variabile booleana che indica se la mano costituisce un "Colore"
    */
    private boolean checkColore(ArrayList<Card> cards) {
        int[] seedCounter = new int[4];
        for (Card carta : cards) {
            seedCounter[carta.getSeed().getValue()]++;
        }
        for (int i = 0; i < seedCounter.length; i++) {
            if (seedCounter[i] == 5) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * Controlla se la mano costituisce la combinazione "Scala"
    * @param cards le carte della mano
    * @return una variabile booleana che indica se la mano costituisce una "Scala"
    */
    private boolean checkScala(ArrayList<Card> cards) {
        boolean spy = true;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue() - cards.get(i + 1).getValue() != 1) {
                spy = false;
            }
        }
        if (cards.get(0).getValue() == 12 && cards.get(1).getValue() == 3) {
            spy = true;
            for (int i = 1; i < cards.size() - 1; i++) {
                if (cards.get(i).getValue() - cards.get(i + 1).getValue() != 1) {
                    spy = false;
                }
            }
        }
        return spy;
    }

    public Board() {
        this.mazzo = new Deck();
        this.giocatori = new ArrayList<>();
        this.hasPlayers = false;
        this.communityCards = new ArrayList<>();
    }

    public ArrayList<Player> getGiocatori() {
        return giocatori;
    }
}