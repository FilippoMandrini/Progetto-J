package poker;

public class HumanPlayer extends Player {
    
    private String psw;

    public HumanPlayer(String name, String psw) {
        super(name);
        this.psw = psw;
    }

    @Override
    public int getStake() {
        return super.getStake();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public String getPsw() {
        return psw;
    }
}