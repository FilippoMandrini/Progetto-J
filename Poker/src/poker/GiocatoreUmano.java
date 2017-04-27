package poker;

public class GiocatoreUmano extends Giocatore {
    
    private String password;

    public GiocatoreUmano(String nome, String password) {
        super(nome);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}