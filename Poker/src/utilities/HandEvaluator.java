/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import exceptions.WrongCardNumberException;
import handtypes.CartaAlta;
import handtypes.Colore;
import handtypes.Coppia;
import handtypes.DoppiaCoppia;
import handtypes.Full;
import handtypes.Hand;
import handtypes.PokerHand;
import handtypes.Scala;
import handtypes.ScalaColore;
import handtypes.Tris;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import poker.Card;
import players.Player;

/**
 *
 * @author Nickelsilver
 */
public abstract class HandEvaluator {

    /**
     * Valuta la miglior mano del giocatore
     * @param player il giocatore
     * @param communityCards carte comuni
     * @return la migliore mano del giocatore
     */
    public static Hand evaluate(Player player, List<Card> communityCards) {
        List<Card> toEvaluate = new ArrayList<>();
        toEvaluate.addAll(player.getCards());
        toEvaluate.addAll(communityCards);
        return evaluateFull(toEvaluate);
    }

    /**
     * Restituisce la combinazione migliore tra tutte le combinazioni di carte
     *
     * @param cards le carte del giocatore e le carte del tavolo
     * @return la mano migliore del giocatore
     * @throws WrongCardNumberException
     */
    @SuppressWarnings("empty-statement")
    private static Hand evaluateFull(List<Card> cards) throws WrongCardNumberException {
        if (cards.size() != 7) {
            throw new WrongCardNumberException("Carte in numero errato");
        }
        List<Hand> results = new ArrayList<>();
        int k = 5;
        int indices[] = new int[k];
        if (k <= cards.size()) {
            for (int i = 0; i < k; i++) {
                indices[i] = i;
            }
            results.add(evaluateSingle(getSubset(cards, indices)));
            while (true) {
                int i;
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
    private static List<Card> getSubset(List<Card> input, int[] subset) {
        List<Card> toHand = new ArrayList(subset.length);
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
     * @throws WrongCardNumberException
     */
    private static Hand evaluateSingle(List<Card> cards) throws WrongCardNumberException {
        if (cards.size() != 5) {
            throw new WrongCardNumberException("Carte in numero errato");
        }
        Collections.sort(cards);
        List<Card> sortedCards = new ArrayList<>();
        int secondMaxIndex = 0;
        int maxIndex = 0;
        int max = 0;
        int[] cardCounter = new int[13];
        for (Card carta : cards) {
            cardCounter[carta.getRank()]++;
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
            if (cards.get(i).getRank() == maxIndex) {
                sortedCards.add(cards.get(i));
                cards.remove(cards.get(i));
                i--;
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getRank() == secondMaxIndex) {
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
    private static boolean checkColore(List<Card> cards) {
        int[] seedCounter = new int[4];
        for (Card carta : cards) {
            seedCounter[carta.getSuit().getValue()]++;
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
    private static boolean checkScala(List<Card> cards) {
        boolean spy = true;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getRank() - cards.get(i + 1).getRank() != 1) {
                spy = false;
            }
        }
        if (cards.get(0).getRank() == 12 && cards.get(1).getRank() == 3) {
            spy = true;
            for (int i = 1; i < cards.size() - 1; i++) {
                if (cards.get(i).getRank() - cards.get(i + 1).getRank() != 1) {
                    spy = false;
                }
            }
        }
        return spy;
    }

}
