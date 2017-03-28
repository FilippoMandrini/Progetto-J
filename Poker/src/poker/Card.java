/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

/**
 *
 * @author Nickelsilver
 */
public class Card {
    
    private int value;
    private Seed seed;

    public int getValue() {
        return value;
    }

    public Seed getSeed() {
        return seed;
    }

    public Card(int value, Seed seed) {
        this.value = value;
        this.seed = seed;
    }
    
}
