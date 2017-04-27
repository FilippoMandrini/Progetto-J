package poker;

import static poker.TipoSeme.*;

public class Seme {
    
    private TipoSeme seme;
    private int valore;

    
    public TipoSeme getSeme() {
        return seme;
    }

    public int getValore() {
        return valore;
    }

    public Seme(TipoSeme seme, int valore) {
        this.seme = seme;
        this.valore = valore;
    }

    public Seme(int valore) {
        this.seme = convertiSeme(valore);
        this.valore = valore;
    }
    
    private TipoSeme convertiSeme(int numero){
        switch (numero){
            case 0:
                return CUORI;
            case 1:
                return PICCHE;
            case 2:
                return FIORI;
            case 3:
                return DENARI;
        }
        return null;
    }
    
}
