package poker;

import exceptions.InvalidPlayerNameException;
import exceptions.PlayerNotFoundException; 
import handtypes.Hand;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe per la gestione della partita
 */
public class Partita {
    
    private Board tavolo;
    private List<Player> giocatori;
    private StakeManager gestorePuntate;
    private PlayerEvaluator gestoreClassifica;
    private int stakeAmount;
    private int pot;
    private int currentBet;

    public Partita(List<Player> giocatori) {
        this.giocatori = giocatori;
        this.gestoreClassifica = new PlayerEvaluator();
        this.gestorePuntate = new StakeManager();
        this.tavolo = new Board();
    }
    
    public Partita() {
        this.giocatori = new ArrayList<>();
        this.gestoreClassifica = new PlayerEvaluator();
        this.gestorePuntate = new StakeManager();
        this.tavolo = new Board();
    }
    
    /**
     * Stampa la lista dei vincitori
     * @return 
     */
    public ArrayList<Player> getRanking() {
        ArrayList<Player> ranking = new ArrayList<>();
        ranking.addAll(giocatori);
        Collections.sort(ranking);
        return ranking;
    }

    /**
     * Ritorna se sono o no presenti i giocatori
     * @return
     */
    public boolean hasPlayers() {
        return giocatori.isEmpty();
    }

    /**
     * Aggiunge un giocatore controllando che non si utilizzi un nome 
     * già scelto da un altro utente
     * @param player il giocatore
     * @return
     */
    
    public boolean addPlayer(Player player) {
        boolean presence = false;
        for (Player giocatore : giocatori) {
            if (giocatore.getName().equals(player.getName())) {
                presence = true;
            }
        }
        if (presence == false) {
            giocatori.add(player);
            player.setStake(2000);
        } else {
            throw new InvalidPlayerNameException("Nome già utilizzato!");
        }
        return giocatori.contains(player);
    }

    /**
     * Ritorna la mano del giocatore 
     * @param player il giocatore
     * @return la mano del giocatore
     */
    public Hand getPlayerHand(Player player) {
        if (giocatori.contains(player)) {
            return player.getCurrent();
        }
        throw new PlayerNotFoundException("Giocatore non trovato!");
    }

    public List<Player> getGiocatori() {
        return giocatori;
    }
}
    
    
    
   
