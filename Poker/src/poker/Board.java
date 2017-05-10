package poker;

import exceptions.InvalidPlayerNameException;
import exceptions.NotEnoughCardsException;
import exceptions.PlayerNotFoundException;
import handtypes.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private Deck mazzo;
    private ArrayList<Player> ranking;
    private boolean hasPlayers;
    private ArrayList<Card> communityCards;

    public void playGame() {
        preflop();
        flop();
        turn();
        river();
        for (Player player : ranking) {
            evaluatePlayer(player);
            System.out.println(player.getName() + " ha in mano " + player.getCurrent());
        }
        getWinners();
    }

    public void getWinners() {
        sortRanking();
        double bestPoints = ranking.get(0).getHandPoints();
        System.out.println("Vincitori: \n");
        for (int i = 0; i < ranking.size(); i++) {
            if (ranking.get(i).getHandPoints() == bestPoints) {
                System.out.println(ranking.get(i).getName() + " con " + ranking.get(i).getCurrent());
            }
        }
    }

    /**
     * Ordina la classifica dei giocatori
     *
     * @return
     */
    public boolean sortRanking() {
        ArrayList<Player> sortedRanking = new ArrayList<>();
        double bestHandPoint;
        Player bestPlayer = null;
        Hand bestHand = null;
        int size = ranking.size();
        while (sortedRanking.size() < size) {
            bestHandPoint = 0;
            for (Player player : ranking) {
                if (player.getCurrent().getPoints() > bestHandPoint) {
                    bestPlayer = player;
                    bestHandPoint = player.getCurrent().getPoints();

                }
            }
            sortedRanking.add(bestPlayer);
            ranking.remove(bestPlayer);
        }
        ranking.addAll(sortedRanking);
        return true;

    }

    public boolean clearBoard() {
        this.communityCards.clear();
        for (Player player : ranking) {
            ranking.add(player);
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

    public boolean preflop() {
        for (Player player : ranking) {
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
        boolean presence = false;
        for (Player giocatore : ranking) {
            if (giocatore.getName().equals(player.getName())) {
                presence = true;
            }
        }
        if (presence == false) {
            ranking.add(player);
        } else {
            throw new InvalidPlayerNameException("Nome già utilizzato!");
        }

        return ranking.contains(player);
    }

    public Hand getPlayerHand(Player player) {
        if (ranking.contains(player)) {
            return player.getCurrent();
        }
        throw new PlayerNotFoundException("Giocatore non trovato!");
    }

    public void evaluatePlayer(Player player) {
        if (!ranking.contains(player)) {
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
        this.ranking = new ArrayList<>();
        this.hasPlayers = false;
        this.communityCards = new ArrayList<>();

    }

    public Deck getMazzo() {
        return mazzo;
    }

}
