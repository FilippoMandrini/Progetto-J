package players;

/**
 *Classe astratta della strategia del bot che implementa il client
 */
public abstract class AIStrategy implements Client{

    @Override
    public boolean isConnected() {
        return true;
    }
    
    @Override
    public boolean isReachable(int timeout) {
        return true;
    }
    
    @Override
    public void setConnected(boolean connected) {
        // non implementato
    }
    
    @Override
    public void disconnect()
    {
        // non implementato
    }
    
    @Override
    public void ping() {
        //non implementato
    }

    @Override
    public boolean isBlocked()
    {
        return false;
    }
}
