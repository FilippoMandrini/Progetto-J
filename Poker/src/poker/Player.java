
package poker;

public abstract class Player {
    
    private String name;
    private int stake;
    
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getStake() {
        return stake;
    }
}