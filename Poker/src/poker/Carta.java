package poker;

import java.util.Objects;


public class Carta implements Comparable {

    private int valore;
    private Seme seme;
    private static final String nomeSeme[] = {"Cuori", "Picche", "Fiori", "Denari"};
    private static final String nomeValore[] = {"Due", "Tre", "Quattro", "Cinque", "Sei",
        "Sette", "Otto", "Nove", "Dieci", "Fante", "Regina", "Re", "Asso"};

    /**
     *
     * @Ritorna il valore numerico della Carta
     */
    public int getValore() {
        return valore;
    }

    /**
     *
     * 
     * @Ritorna la Stringa corrispondente al valore della Carta
     */
    public static String getNomeValore(int valore) {
        return nomeValore[valore];
    }
    
    /**
     *
     * @param seme
     * @return
     */
    public static String getNomeSeme(Seme seme) {
        
        return nomeSeme[seme.getValore()];
    }

    /**
     *
     * @Ritorna il seme della Carta
     */
    public Seme getSeme() {
        return seme;
    }

    public Carta(int valore, Seme seme) {
        this.valore = valore;
        this.seme = seme;
    }


    public Carta(int valore) {
        this.valore = valore;
    }
    
    

    @Override
    public String toString() {
        return nomeValore[valore] + " di " + nomeSeme[seme.getValore()];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (this.valore != other.valore) {
            return false;
        }
        if (!Objects.equals(this.seme, other.seme)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object t) {

        final Carta other = (Carta) t;
        return other.getValore() - this.getValore();
    }

}
