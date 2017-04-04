
package poker;


public class Player {
    
    private String name;
    private String psw;
    private int stake;

    public Player(String name, String psw) {
        this.name = name;
        this.psw = psw;
        this.stake = 200;
    }

    public String getName() {
        return name;
    }

    public String getPsw() {
        return psw;
    }

    public int getStake() {
        return stake;
    }
}
