package poker;

import players.Player;
import actions.*;
import exceptions.*;
import gametypes.*;
import handtypes.*;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.*;
import utilities.HandEvaluator;

/**
 * Classe per la gestione della partita
 */
public class Game extends GameObservable implements Runnable {

    private final GameType settings;
    private final Board board;
    private final List<Player> players;
    private final List<Player> activePlayers;
    private Player currentPlayer;
    private int currentPlayerPosition;
    private Player dealer;
    private int dealerPosition;
    private final PotHandler potHandler;
    private int bet;
    private int minBet;
    private Player lastAggressor;
    private Action currentAction;
    private int raises;
    private int currentBigBlind;
    private int currentHandStage;
    private final GameFacade facade;

    /**
     * Costruttore di Game
     * @param settings le impostazioni del tavolo
     */
    public Game(GameType settings){
        this.settings = settings;
        this.currentBigBlind = settings.getBigBlind();
        players = new ArrayList<>();
        activePlayers = new ArrayList<>();
        board = new Board();
        potHandler = new PotHandler();
        facade = new GameFacade(this);
    }
    
    /**
     * Lancia la partita
     */
    @Override
    public void run() 
    {
        this.playGame();
    }
 
    /**
     * Esegue la partita
     * Gestisce la lista dei giocatori e se possono giocare. Se ci sono almeno due giocatori
     * inizia una nuova mano
     */
    public void playGame()
    {
        notifyGameStarted(players, settings);
        int noOfHands = 0;
        dealerPosition = -1;
        currentPlayerPosition = -1;
        while(true)
        {
            int playersAbleToPlay = playersAbleToPlay(settings.isOnlyHumans());
            if (playersAbleToPlay > 1)
            {
                //notifyMessageUpdated("Mano n° " + (noOfHands + 1));
                playSingleHand();
                noOfHands++;
            }
            else
            {
                break;
            }
        }
        board.clear();
        potHandler.clearPots();
        notifyBoardUpdated(board);
        notifyBettingUpdated(bet, minBet, potHandler.getTotalPot());
        bet = 0;
        for (Player player : players)
        {
            player.reset();
            player.setActive(false);
        }
        notifyHiddenPlayersUpdated(players);
        setActivePlayers();
        if (activePlayers.get(0).getStake()!= 0)
            notifyMessageUpdated("Game Over : " + activePlayers.get(0).getName() + " ha vinto");
        notifyDisconnect();
    }
    
    /**
     * Gioca una mano richiamando i metodi attraverso le varie fasi
     */
    private void playSingleHand()
    {
        resetHand();
        if (activePlayers.size() > 2)
        {
            shiftCurrentPlayer();
        }
        try 
        {
            Thread.sleep(settings.getAIdelay());
        } 
        catch (InterruptedException ex) {}
        betSmallBlind();
        shiftCurrentPlayer();
        try 
        {
            Thread.sleep(settings.getAIdelay());
        } 
        catch (InterruptedException ex) {}
        betBigBlind();
        preFlop();
        if (activePlayers.size() > 1) 
        {
            nextHandStage(3);
            if (activePlayers.size() > 1) 
            {
                nextHandStage(1);
                if (activePlayers.size() > 1) 
                {
                    nextHandStage(1);
                    if (activePlayers.size() > 1) 
                    {
                        showdown();
                    }
                }
            }
        }
    }    
    
    /**
     * Reinizializza il tavolo per poter iniziare una nuova mano.
     * Pulisce il tavolo, le puntate, le carte date, reinizializza il mazzo e
     * imposta il Dealer, i giocatori attivi e le puntate di partenza
     */
    private void resetHand()
    {
        checkClientConnections();
        currentHandStage = 0;
        board.clear();
        potHandler.clearPots();
        notifyBoardUpdated(board);
        notifyBettingUpdated(bet, minBet, potHandler.getTotalPot());
        activePlayers.clear();
        for (Player player: players)
        {
            player.reset();
        }
        setActivePlayers();
        shiftDealer();
        currentPlayerPosition = dealerPosition;
        currentPlayer = activePlayers.get(currentPlayerPosition);
        minBet = currentBigBlind;
        bet = currentBigBlind;
        notifyHandStarted(dealer, dealerPosition);
        notifyHiddenPlayersUpdated(players);
    }
    
    /**
     * Esegue un giro di puntate in base al tipo di azione scelto e al valore 
     * dell'eventuale scommessa
     * Gestisce la rotazione dei giocatori e la correttezza delle puntate
     * @throws IllegalActionException quando il giocatore compie un'azione vietata
     */
    private void bettingRound ()
    {
        int playersLeft = activePlayers.size();
        if (currentHandStage != 1)
        {
            currentPlayerPosition = dealerPosition;
            bet = 0;
            minBet = currentBigBlind;
        }
        lastAggressor = null;
        raises = 0;
        notifyBettingUpdated(bet, minBet, potHandler.getTotalPot());
        while (playersLeft > 0)
        {
            shiftCurrentPlayer();
            playersLeft = askAndExecuteAction(playersLeft);
            notifyBettingUpdated(bet, minBet, potHandler.getTotalPot());
            notifyCurrentPlayerActed(currentPlayer);
        }
        try 
        {
            Thread.sleep(settings.getAIdelay());
        } 
        catch (InterruptedException ex) {}
        for (Player player : activePlayers) 
        {
            player.setCurrentBet(0);
            player.setLastAction(null);
        } 
        notifyBettingUpdated(bet, minBet, potHandler.getTotalPot());
        notifyHiddenPlayersUpdated(players);
    }
    
    /**
     * Richiede un'azione al giocatore e la esegue
     * @param playersLeft il numero dei giocatori che devono agire
     * @throws IllegalActionException quando l'azione non è consentita
     * @return il numero di giocatori che devono ancora agire dopo l'azione
     */
    private int askAndExecuteAction(int playersLeft) 
    {
        Action action = null;
        try 
        {
            if (currentPlayer.isAllIn()) 
            {
                action = new Check();
            } 
            else 
            {
                Set<ActionSet> allowedActions = getActionSet(currentPlayer);
                action = currentPlayer.getClient().act(minBet, bet, allowedActions);
                if (action == null || !(allowedActions.contains(action.getActionType()))) 
                {
                    throw new IllegalActionException("Azione non possibile!");
                }
            }
        } 
        catch (SocketTimeoutException e) 
        {
            action = new Fold();
            currentPlayer.getClient().disconnect();
            disconnectPlayer(currentPlayer);
            notifyHiddenPlayersUpdated(players);
        } 
        catch (IllegalActionException | IOException e) 
        {
            action = new Fold();
            disconnectPlayer(currentPlayer);
            notifyHiddenPlayersUpdated(players);
        }
        playersLeft = action.execute(facade, playersLeft);
        currentAction = action;
        currentPlayer.setLastAction(action);
        return playersLeft;
    }
    
    /**
     * Ritorna un set delle mosse che il giocatore può compiere
     * @param player il giocatore
     * @return set delle mosse che il giocatore può compiere
     */
    private Set<ActionSet> getActionSet(Player player)
    {
        Set<ActionSet> allowedActions = new HashSet<>();
        if (player.isAllIn())
        {
            allowedActions.add(ActionSet.CHECK);
        }
        else
        {
            int currentPlayerBet = currentPlayer.getCurrentBet();
            int currentPlayerStake = currentPlayer.getStake();
            if (bet == 0)
            {
                allowedActions.add(ActionSet.CHECK);
                allowedActions.add(ActionSet.BET);
            }
            else if (currentPlayerBet < bet)
            {
                allowedActions.add(ActionSet.CALL);
                if(raises < settings.getMaxRaises() && (bet - currentPlayerBet) < currentPlayerStake)
                {
                    allowedActions.add(ActionSet.RAISE);
                }
            }
            else
            {
                allowedActions.add(ActionSet.CHECK);
                if(raises < settings.getMaxRaises() && (bet - currentPlayerBet) < currentPlayerStake)
                {
                    allowedActions.add(ActionSet.RAISE);
                }
            }
            allowedActions.add(ActionSet.FOLD);
        }
        return allowedActions;
    }
    
    /**
     * Ruota la posizione del giocatore che deve agire
     */
    private void shiftCurrentPlayer()
    {
        currentPlayerPosition = (currentPlayerPosition +1) % (activePlayers.size());
        currentPlayer = activePlayers.get(currentPlayerPosition);
        notifyCurrentPlayerUpdated(currentPlayer, currentPlayerPosition);
    }
    
    /**
     * Cambia la posizione del Dealer
     */
    private void shiftDealer()
    {
        dealerPosition = (dealerPosition +1) % (activePlayers.size());
        dealer = activePlayers.get(dealerPosition);
    }
    
    /**
     * Scommette il piccolo buio
     */
    private void betSmallBlind()
    {
        currentAction = new SmallBlind(currentBigBlind/2);
        currentPlayer.setLastAction(currentAction);
        currentAction.execute(facade, 0);
        notifyCurrentPlayerActed(currentPlayer);
        notifyBettingUpdated(bet, minBet, potHandler.getTotalPot());
    }
    
    /**
     * Scommette il grande buio
     */
    private void betBigBlind()
    {
        currentAction = new BigBlind(currentBigBlind);
        currentPlayer.setLastAction(currentAction);
        currentAction.execute(facade, 0);
        notifyCurrentPlayerActed(currentPlayer);
        notifyBettingUpdated(bet, minBet, potHandler.getTotalPot());
    }
    
    /**
     * Esegue la fase successiva della mano, scoprendo delle carte comuni
     * @param noOfCards numero di carte comuni da scoprire
     */
    private void nextHandStage(int noOfCards)
    {
        currentHandStage++;
        bet = 0;
        minBet = currentBigBlind;
        this.board.dealCommunityCards(noOfCards);
        notifyBoardUpdated(board);
        bettingRound();
    }
    
    /**
     * Esegue il turno del pre Flop
     * Consegna le due carte ai giocatori
     */
    private void preFlop()
    {
        currentHandStage++;
        for(Player player : activePlayers)
        {
            this.board.dealHoleCards(player, 2);
        }
        notifyHiddenPlayersUpdated(players);
        bettingRound();
    }

    /**
     * Mostra tutte le carte di tutti i giocatori 
     * Determina i vincitori e assegna le vincite corrette
     */
    private void showdown()
    {
        currentHandStage = 5;
        bet = 0;
        for (Player player : activePlayers)
        {
            player.setCurrentHand(HandEvaluator.evaluate(player, board.getCommunityCards()));
        }
        Set<Player> playersToShow = new HashSet<>();
        for (Pot pot : potHandler.getPots())
        {
            for (Player member : pot.getMembers())
            {
                if (!playersToShow.contains(member) && member.getCards() != null && member.isAllIn())
                {
                    playersToShow.add(member);
                }
            }
            if (lastAggressor != null)
            {
                playersToShow.add(lastAggressor);
            }
            int position = (dealerPosition +1) % activePlayers.size();
            while (playersToShow.size() < activePlayers.size())
            {
                playersToShow.add(activePlayers.get(position));
                position = (position + 1) % activePlayers.size();
            }
        }
        List<Player> playersToShowList = new ArrayList<>(playersToShow);
        notifyPlayersUpdated(playersToShowList);
        potHandler.distributePots(getRanking(), activePlayers, dealerPosition);
        notifyPlayersUpdated(players);
        try 
        {
            Thread.sleep(5000);
        } 
        catch (InterruptedException ex) {}
    }

    /**
     * Restituisce la classifica dei giocatori in base alle mani 
     * @return la classifica dei giocatori in base alle mani
     */
    private Map<Hand, List<Player>> getRanking()
    {
        Map<Hand, List<Player>> ranking = new TreeMap<>();
        for (Player player : activePlayers)
        {
            List<Player> currentHandPlayers = ranking.get(player.getCurrentHand());

            if (currentHandPlayers == null)
            {
                currentHandPlayers = new ArrayList<>();
            }
            currentHandPlayers.add(player);
            ranking.put(player.getCurrentHand(), currentHandPlayers);
        }
        return ranking;
    }
        
    /**
     * Imposta se i giocatori risultano attivi oppure no
     */
    public void setActivePlayers()
    {
        for (Player player: players)
        {
            if (player.getStake() >= currentBigBlind)
            {
                player.setActive(true);
                activePlayers.add(player);
            }
            else
            {
                player.setActive(false);
            }
        }
    }
    
    /**
     * Aggiunge un giocatore 
     * @param player il giocatore
     */
    public void addPlayer(Player player) 
    {

        if (!players.contains(player)) 
        {
            players.add(player);
            player.setId(players.size());
            player.setStake(settings.getStartingStake());
        } 
        else 
        {
            throw new InvalidPlayerNameException("Player già presente!");
        }
    }
    
    /**
     * Elimina il giocatore disconnesso
     * @param player il giocatore disconnesso
     */
    public void disconnectPlayer(Player player)
    {
        if(players.contains(player))
        {
            player.setStake(0);
            player.setCurrentBet(0);
            player.setName("Disconnesso");
            this.observers.remove(player.getClient());
        }
    }
    
    /**
     * Controlla se i client sono connessi per rimuovere quelli disconnessi
     */
    public void checkClientConnections()
    {
        boolean spy = false;
        for (Player player : players)
        {
            if (!player.getClient().isConnected())
            {
                disconnectPlayer(player);
                spy= true;
            }
        }
        if (spy)
            notifyHiddenPlayersUpdated(players);
    }
    
    /**
     * Controlla quanti giocatori (umani e bot) possono giocare
     * @param onlyHumans true se sono ammessi solo giocatori umani, false altrimenti
     * @return il numero dei giocatori che possono giocare
     */
    private int playersAbleToPlay(boolean onlyHumans) 
    {
        int noOfPlayers = 0;
        int noOfHumans = 0;
        for (Player player : players)
        {
            if (player.getClient().isHuman()) 
            {
                if (player.getStake() >= currentBigBlind) 
                {
                    noOfHumans++;
                }
            }     
        }
        if (noOfHumans > 0 || !onlyHumans)
        {
            for (Player player : players)
            {
                if (player.getStake() >= currentBigBlind) 
                {
                    noOfPlayers++;
                }            
            }
        }
        return noOfPlayers;
    }


    /**
     * Restituisce i giocatori
     * @return la lista dei giocatori
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Ritorna le impostazioni del tavolo
     * @return le impostazioni del tavolo
     */
    public GameType getSettings() {
        return settings;
    }

    /**
     * Ritorna se sono o no presenti i giocatori
     * @return true se sono presenti dei giocatori, false altrimenti
     */
    public boolean hasPlayers() {
        return !players.isEmpty();
    }

    /**
     * Ritorna la lista dei giocatori attivi
     * @return la lista dei giocatori attivi
     */
    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    /**
     * Ritorna il giocatore corrente
     * @return il giocatore corrente
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Ritorna la posizione del giocatore corrente
     * @return la posizione del giocatore corrente
     */
    public int getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    /**
     * Ritorna il gestore dei pots
     * @return il gestore dei pots
     */
    public PotHandler getPotHandler() {
        return potHandler;
    }

    /**
     * Ritorna il valore della puntata
     * @return il valore della puntata
     */
    public int getBet() {
        return bet;
    }

    /**
     * Ritorna il valore della puntata minima
     * @return il valore della puntata minima
     */
    public int getMinBet() {
        return minBet;
    }

    /**
     * Ritorna il numero dei raise
     * @return il numero dei raise
     */
    public int getRaises() {
        return raises;
    }

    /**
     * Ritorna il valore del grande buio
     * @return il valore del grande buio
     */
    public int getCurrentBigBlind() {
        return currentBigBlind;
    }

    /**
     * Imposta la posizione del giocatore attuale
     * @param currentPlayerPosition la posizione del giocatore attuale
     */
    public void setCurrentPlayerPosition(int currentPlayerPosition) {
        this.currentPlayerPosition = currentPlayerPosition;
    }

    /**
     * Imposta il valore della scommessa
     * @param bet il valore della scommessa
     */
    public void setBet(int bet) {
        this.bet = bet;
    }

    /**
     * Imposta il valore della scommessa minima
     * @param minBet il valore della scommessa minima
     */
    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }

    /**
     * Imposta l'ultimo giocatore aggressore
     * @param lastAggressor l'ultimo giocatore aggressore
     */
    public void setLastAggressor(Player lastAggressor) {
        this.lastAggressor = lastAggressor;
    }

    /**
     * Imposta il giocatore attuale
     * @param currentPlayer il giocatore attuale
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Restituisce la GameFacade
     * @return la GameFacade
     */
    public synchronized GameFacade getFacade() {
        return facade;
    }

}
