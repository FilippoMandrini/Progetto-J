package poker;

public class HumanPlayer extends Player {
    
    private String psw;

    public HumanPlayer(String name, String psw) {
        super(name);
        this.psw = psw;
    }

    public String getPsw() {
        return psw;
    }
}