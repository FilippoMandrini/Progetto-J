package utilities;

import exceptions.WrongCardNumberException;
import java.util.List;
import poker.Card;

/**
 * Classe astratta utlizzata per assegnare un punteggio alle proprie due carte 
 * in modo da avere un set di azioni prefissato per un determinato range di punteggi
 * (utilizzata per il bot AI)
 */
public abstract class ChenEvaluator {

    /**
     * Applica la formula di Chen, che si basa sul valore della carta che vale di più,
     * sul loro seme se uguale o diverso e sulla loro distanza di valore.
     * Il punteggio vale al massimo 20 punti
     * @param cards le due carte del giocatore
     * @throws WrongCardNumberException quando il numero delle carte è diverso da due 
     * @return il punteggio arrotondato delle carte
     */
    public static double evaluate(List<Card> cards) {
        
        if (cards.size() != 2) 
        {
            throw new WrongCardNumberException("Carte in numero non valido: " + cards.size());
        }

        int firstRank = cards.get(0).getRank();
        int firstSuit = cards.get(0).getSuit().getValue();
        int secondRank = cards.get(1).getRank();
        int secondSuit = cards.get(1).getSuit().getValue();
        int maxRank = Math.max(firstRank, secondRank);
        int minRank = Math.min(firstRank, secondRank);
        int rankDifference = maxRank - minRank;
        int gap;
        if (rankDifference > 1)
        {
            gap = rankDifference - 1;
        }
        else
        {
            gap = 0;
        }
        boolean isPair = (firstRank == secondRank);
        boolean haveSameSuit = (firstSuit == secondSuit);
        double score = 0;
        
        switch (maxRank) 
        {
            case 12:
                score = 10;
                break;
            case 11:
                score = 8;
                break;
            case 10:
                score = 7;
                break;
            case 9:
                score = 6;
                break;
            default:
                score = (maxRank + 2) / 2.0;
                break;
        }
        if (isPair) 
        {
            score *= 2;
            if (score < 5) 
            {
                score = 5;
            }
        }
        if (haveSameSuit) 
        {
            score += 2;
        }
        
        if (gap == 1) 
        {
            score -= 1;
        } 
        else if (gap == 2) 
        {
            score -= 2;
        } 
        else if (gap == 3) 
        {
            score -= 4;
        } 
        else if (gap > 3) 
        {
            score -= 5;
        }
        
        if (!isPair && gap < 2 && firstRank < 10 && secondRank < 10) 
        {
            score += 1;
        }

        if (score < 0)
        {
            score = 0;
        }

        return Math.round(score);
    }

}
