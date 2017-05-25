/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import exceptions.WrongCardNumberException;
import java.util.List;
import poker.Card;

/**
 *
 * @author Nickelsilver
 */
public abstract class ChenEvaluator {

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
