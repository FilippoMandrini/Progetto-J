package poker;

import exceptions.NotEnoughCardsException;
import exceptions.PlayerNotFoundException;
import handtypes.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Board {

    private Deck mazzo;
    private ArrayList<HumanPlayer> giocatori;
    private HashMap<Player, Hand> ranking;
    private boolean hasPlayers;
    private ArrayList<Card> communityCards;
    
    public void playGame(){
        clearBoard();
        preflop();
        flop();
        turn();
        river();
        for(Player player : ranking.keySet()){
            evaluatePlayer(player);      
        }
         //INSERIRE METODO CHE TROVA IL MIGLIORE FRA TUTTI I PLAYER
    }
    
    public boolean clearBoard() {
        this.communityCards.clear();
        for (Player player : ranking.keySet()) {
            ranking.put(player, null);
        }
        this.mazzo.restore();
        return true;
    }

    public boolean terminateBoard() {
        clearBoard();
        this.ranking.clear();
        return true;
    }

    public boolean dealCards(Player player) {
        player.clearCards();
        for (int i = 0; i < 2; i++) {
            player.addCard(this.mazzo.getCard());
        }
        return true;
    }

    public boolean preflop(){
        for(Player player : ranking.keySet()){
            dealCards(player);       
        }
        return true;
    }
    
    public boolean flop() {
        for (int i = 0; i < 3; i++) {
            communityCards.add(mazzo.getCard());
        }
        return true;
    }

    public boolean turn() {
        mazzo.burnCard();
        communityCards.add(mazzo.getCard());
        return true;
    }

    public boolean river() {
        mazzo.burnCard();
        communityCards.add(mazzo.getCard());
        return true;
    }

    public boolean hasPlayers() {
        return hasPlayers;
    
    }

    public boolean addPlayer(Player player) {
        if (!ranking.containsKey(player)) {
            ranking.put(player, null);
        }
        return ranking.containsKey(player);
    }
  
    public Hand getPlayerHand(Player player) {
        if (ranking.containsKey(player) && ranking.get(player) != null) {
            return ranking.get(player);
        }
        throw new PlayerNotFoundException("Giocatore non trovato!");
    }

    public void evaluatePlayer(Player player) {
        if (!ranking.containsKey(player)) {
            throw new PlayerNotFoundException("Giocatore non trovato!");
        }
        ArrayList<Card> toEvaluate = new ArrayList<>();
        toEvaluate.addAll(player.getPlayerCards());
        toEvaluate.addAll(this.communityCards);
        ranking.put(player, evaluateFull(toEvaluate));
        
    }

    /**
     * Restituisce la combinazione migliore tra tutte le combinazioni di carte
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
                if (i < 0) 
                {
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

            return new Poker(sortedCards);
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

    public Deck getMazzo() {
        return mazzo;
    }

    public ArrayList<HumanPlayer> getGiocatori() {
        return giocatori;
    }
}
