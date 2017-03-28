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
public class Seed {
    
    private SeedType seed;
    private int value;

    public SeedType getSeed() {
        return seed;
    }

    public int getValue() {
        return value;
    }

    public Seed(SeedType seed, int value) {
        this.seed = seed;
        this.value = value;
    }
    
    
}
