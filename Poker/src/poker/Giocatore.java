
package poker;

public abstract class Giocatore {
    
    private String nome;
    private int stake;
    
    public Giocatore(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getStake() {
        return stake;
    }
}