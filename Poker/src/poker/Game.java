package poker;

import players.Player;
import actions.*;
import exceptions.*;
import gametypes.*;
import handtypes.*;
import java.util.*;
import utilities.HandEvaluator;

/**
 * Classe per la gestione della partita
 */
public class Game extends GameObservable {
    
    private final GameType settings;
    
    private final Board board;
        
    private final List<Player> players;
    
    private final List<Player> activePlayers;
    
    private Player currentPlayer;
    
    private int currentPlayerPosition;
    
    private Player dealer;
   
    private int dealerPosition;
    
    private final List<Pot> pots;
    
    private int bet;

    private int minBet;
    
    private Player lastAggressor;
    
    private Action currentAction;
    
    private int raises;
    
    private int currentBigBlind;
    
    private int currentHandStage;
  
    
    
    /**
     * Costruttore di Game
     * @param settings le impostazioni del tavolo
     */
    public Game(GameType settings)
    {
        this.settings = settings;
        this.currentBigBlind = settings.getBigBlind();
        players = new ArrayList<>();
        activePlayers = new ArrayList<>();
        board = new Board();
        pots = new ArrayList<>();
    }
    
    /**
     * metodo principale del gioco
     */
    public void playGame()
    {
        System.out.println("[TEST] Giocatori: ");
        for (Player player : players)
        {
            notifyGameStarted();
            System.out.println("[TEST] " + player.toString() + " con stake " + player.getStake());
        }
        System.out.println("[TEST] Inizio Partita...");
        int noOfHands = 0;
        dealerPosition = -1;
        currentPlayerPosition = -1;
        while(true)
        {
            int playersAbleToPlay = 0;
            for (Player player : players)
            {
                if (player.getStake() >= currentBigBlind)
                {
                    playersAbleToPlay++;
                }
            }
            if (playersAbleToPlay > 1)
            {
                System.out.println("\n\n");
                System.out.println("[TEST] Mano n°: " + (noOfHands + 1));
                playSingleHand();
                noOfHands++;
            }
            else
            {
                break;
            }
        }
        board.clear();
        pots.clear();
        notifyBoardUpdated(board);
        notifyBettingUpdate(bet, minBet, getTotalPot());
        bet = 0;
        for (Player player : players)
        {
            player.reset();
            player.setActive(false);
        }
        notifyHiddenPlayersUpdated(players);
        System.out.println("Game Over");
        notifyMessageUpdated("Game Over");
    }
    /**
     * metodo che gioca una mano
     */
    private void playSingleHand()
    {
        resetHand();
        if (activePlayers.size() > 2)
        {
            shiftCurrentPlayer();
        }
        betSmallBlind();
        shiftCurrentPlayer();
        betBigBlind();
        currentHandStage = 1;
        preFlop();
        if (activePlayers.size() > 1) 
        {
            flop();
            if (activePlayers.size() > 1) 
            {
                turn();
                if (activePlayers.size() > 1) 
                {
                    river();
                    if (activePlayers.size() > 1) 
                    {
                        showdown();
                    }
                }
            }
        }
    }    
    
    private void resetHand()
    {
        currentHandStage = 0;
        board.clear();
        pots.clear();
        notifyBoardUpdated(board);
        notifyBettingUpdate(bet, minBet, getTotalPot());
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
        notifyHandStarted(dealer);
        notifyHiddenPlayersUpdated(players);
    }
    
    /**
     * Metodo del giro di puntate
     */
    private void bettingRound()
    {
        int playersLeft = activePlayers.size();
        if (currentHandStage == 1)
        {
            minBet = currentBigBlind;
            bet = currentBigBlind;
        }
        else
        {
            currentPlayerPosition = dealerPosition;
            bet = 0;
            minBet = currentBigBlind;
        }
        lastAggressor = null;
        raises = 0;
        notifyBettingUpdate(bet, minBet, getTotalPot());
        while (playersLeft > 0)
        {
            shiftCurrentPlayer();
            Action action = null;
            if (!currentPlayer.getCards().isEmpty() && currentPlayer.getStake() == 0) 
            {
                action = new Check();
                playersLeft--;
            }
            else
            {
                Set<ActionSet> allowedActions = getActionSet(currentPlayer);
                // chiedo al client l'azione da fare, simulo come Azione Generica
                action = currentPlayer.getClient().act(minBet, bet, allowedActions);
                if(action == null || !(allowedActions.contains(action.getActionType())))
                {
                    throw new IllegalActionException("Azione illegale");
                }
                playersLeft--;
                if (action instanceof Call)
                {
                    int callAmount = bet - currentPlayer.getCurrentBet();
                    if(callAmount > currentPlayer.getStake())
                    {
                        callAmount = currentPlayer.getStake();
                    }
                    currentPlayer.pay(callAmount);
                    currentPlayer.setCurrentBet(currentPlayer.getCurrentBet() + callAmount);
                    addToPot(callAmount);
                    action.setAmount(callAmount);
                }
                else if (action instanceof Bet)
                {
                    int betAmount = action.getAmount();
                    if (betAmount < minBet && betAmount < currentPlayer.getStake())
                    {
                        throw new IllegalActionException("Azione illegale");
                    }
                    currentPlayer.setCurrentBet(betAmount);
                    playersLeft = activePlayers.size() - 1;
                    currentPlayer.pay(betAmount);
                    addToPot(betAmount);
                    bet = betAmount;
                    minBet =  betAmount;
                    lastAggressor = currentPlayer;
                }
                else if (action instanceof Raise)
                {
                    int amount = action.getAmount();
                    if (amount < minBet && amount < currentPlayer.getStake())
                    {
                        throw new IllegalActionException("Azione illegale");
                    }
                    bet += amount;
                    minBet = bet;
                    int raiseAmount = bet - currentPlayer.getCurrentBet();
                    if(raiseAmount > currentPlayer.getStake())
                    {
                        raiseAmount = currentPlayer.getStake();
                        bet = raiseAmount;
                        minBet = raiseAmount;
                    }    
                    currentPlayer.pay(raiseAmount);
                    addToPot(raiseAmount);
                    currentPlayer.setCurrentBet(currentPlayer.getCurrentBet() + raiseAmount);
                    playersLeft = activePlayers.size() - 1;
                }                    
                else if (action instanceof Fold) 
                {
                    activePlayers.remove(currentPlayer);
                    currentPlayer.foldCards();
                    currentPlayerPosition--;
                    if (activePlayers.size() == 1)
                    {
                        notifyBettingUpdate(bet, minBet, getTotalPot());
                        notifyCurrentPlayerActed(currentPlayer);
                        activePlayers.get(0).win(getTotalPot());
                        playersLeft = 0;
                    }
                }
                else if (action instanceof Check)
                {
                    //non fare nulla
                }
                else
                {
                    throw new IllegalActionException("Azione illegale");
                }
                System.out.println("[TEST] "+ currentPlayer.toString() + " " + action.getDescription() + " " + action.getAmount());
                System.out.println("[TEST] Pot totale: " + getTotalPot());
                System.out.println("[TEST] RPM: " + playersLeft);
            }
            currentAction = action;
            currentPlayer.setLastAction(action);
            if(playersLeft > 0)
            {
                notifyBettingUpdate(bet, minBet, getTotalPot());
                notifyCurrentPlayerActed(currentPlayer);
            }
        }
        for (Player player : players) 
        {
            player.setCurrentBet(0);
            player.setLastAction(null);
        } 
        notifyBettingUpdate(bet, minBet, getTotalPot());
        notifyHiddenPlayersUpdated(players);
    }
    
    private Set<ActionSet> getActionSet(Player player)
    {
        Set<ActionSet> allowedActions = new HashSet<>();
        if (!player.getCards().isEmpty() && player.getStake() == 0)
        {
            allowedActions.add(ActionSet.CHECK);
        }
        else
        {
            int currentPlayerBet = currentPlayer.getCurrentBet();
            if (bet == 0)
            {
                allowedActions.add(ActionSet.CHECK);
                allowedActions.add(ActionSet.BET);
            }
            else if (currentPlayerBet < bet)
            {
                allowedActions.add(ActionSet.CALL);
                if(raises < settings.getMaxRaises())
                {
                    allowedActions.add(ActionSet.RAISE);
                }
            }
            else
            {
                allowedActions.add(ActionSet.CHECK);
                if (raises < settings.getMaxRaises())                
                {
                    allowedActions.add(ActionSet.RAISE);
                }
            }
            allowedActions.add(ActionSet.FOLD);
        }
        return allowedActions;
    }
    
    /**
     * cambia ruotando la posizione del giocatore che deve agire
     */
    private void shiftCurrentPlayer()
    {
        currentPlayerPosition = (currentPlayerPosition +1) % (activePlayers.size());
        currentPlayer = activePlayers.get(currentPlayerPosition);
        notifyCurrentPlayerUpdated(currentPlayer);
        System.out.println("[TEST] Current Player: " + currentPlayer.toString());
    }
    
    private void shiftDealer()
    {
        dealerPosition = (dealerPosition +1) % (activePlayers.size());
        dealer = activePlayers.get(dealerPosition);
        System.out.println("[TEST] Current Dealer: " + dealer.toString());
    }
    
    private void betSmallBlind()
    {
        currentPlayer.pay(currentBigBlind/2);
        currentPlayer.setLastAction(new SmallBlind(currentBigBlind/2));
        currentPlayer.setCurrentBet(currentBigBlind/2);
        addToPot(currentBigBlind/2);
        notifyBettingUpdate(bet, minBet, getTotalPot());
        notifyCurrentPlayerActed(currentPlayer);
        System.out.println("[TEST] "+ currentPlayer.toString() + " paga Small Blind: " + currentBigBlind/2);
    }
    
    private void betBigBlind()
    {
        currentPlayer.pay(currentBigBlind);
        currentPlayer.setLastAction(new BigBlind(currentBigBlind));
        currentPlayer.setCurrentBet(currentBigBlind);
        addToPot(currentBigBlind);
        notifyBettingUpdate(bet, minBet, getTotalPot());
        notifyCurrentPlayerActed(currentPlayer);
        System.out.println("[TEST] "+ currentPlayer.toString() + " paga Big Blind: " + currentBigBlind);
    }
    
    private void preFlop()
    {
        for(Player player : activePlayers)
        {
            this.board.dealCards(player);
            notifyHiddenPlayersUpdated(players);
            System.out.println("[TEST] "+ player.toString() + " ha carte: ");
            for(Card card : player.getCards())
            {
                System.out.println("[TEST] " + card.toString());
            }
        }
        currentHandStage = 1;
        bettingRound();

    }
    
    private void flop()
    {
        bet = 0;
        minBet = currentBigBlind;
        this.board.flop();
        notifyHiddenPlayersUpdated(players);
        notifyBoardUpdated(board);
        System.out.println("[TEST] Flop...");
        for(Card card : board.getCommunityCards())
        {
            System.out.println("[TEST] " + card.toString());
        }
        currentHandStage = 2;
        bettingRound();
    }
    
    private void turn()
    {
        bet = 0;
        this.board.turn();
        notifyHiddenPlayersUpdated(players);
        notifyBoardUpdated(board);
        System.out.println("[TEST] Turn...");
        for(Card card : board.getCommunityCards())
        {
            System.out.println("[TEST] " + card.toString());
        }
        currentHandStage = 3;
        bettingRound();
    }
    
    private void river()
    {
        bet = 0;
        this.board.river();
        notifyHiddenPlayersUpdated(players);
        notifyBoardUpdated(board);
         System.out.println("[TEST] River...");
        for(Card card : board.getCommunityCards())
        {
            System.out.println("[TEST] " + card.toString());
        }
        currentHandStage = 4;
        bettingRound();
    }
    
    private void showdown()
    {
        bet = 0;
        Set<Player> playersToShow = new HashSet<>();
        for (Pot pot : pots)
        {
            for (Player member : pot.getMembers())
            {
                if (!playersToShow.contains(member) && member.getCards() != null && member.getStake() == 0)
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
        Map<Hand, List<Player>> ranking = getRanking();
        showCards(playersToShow);
        System.out.println("[TEST] Classifica...");
        for (Hand hand : ranking.keySet())
        {
            for (Player player : ranking.get(hand))
            {
                System.out.println("[TEST] " + player.toString());
            }
        }
        distributeWinnings(ranking);   
    }
   
    private void showCards(Set<Player> playersToShow)
    {
        currentHandStage = 5;
        System.out.println("[TEST] Showdown...");
        for(Player playerToShow : playersToShow)
        {
            for (Player player : players)
            {
                player.getClient().playerUpdated(player);
            }
            System.out.println("[TEST] " + playerToShow.toString() + " ha la mano: ");
            System.out.println(playerToShow.getCurrentHand().toString());
        }
    }
    
    private void distributeWinnings(Map<Hand, List<Player>> ranking)
    {
        System.out.println("[TEST] Distribuzione vincite...");
        Map<Player, Integer> winners =  new HashMap<>();
        int totalPot = getTotalPot();
        for (Hand hand : ranking.keySet())
        {
            List<Player> handWinners = ranking.get(hand);
            for (Pot pot : pots) 
            {
                int potWinners = 0;
                for (Player handWinner : handWinners) 
                {
                    if (pot.hasMember(handWinner)) 
                    {
                        potWinners++;
                    }
                }
                if (potWinners > 0)
                {
                    int potShare =  pot.getValue() / potWinners;
                    for (Player handWinner : handWinners)
                    {
                        if (pot.hasMember(handWinner))
                        {
                            Integer previousShare = winners.get(handWinner);
                            if (previousShare == null)
                            {
                                previousShare = 0;
                            }
                            winners.put(handWinner, previousShare + potShare);                            
                        }

                    }
                    int chipsLeft = pot.getValue() % potWinners;
                    if (chipsLeft > 0)
                    {
                        int position = dealerPosition;
                        for (int i = 0; chipsLeft > 0; chipsLeft--)
                        {
                            position = (position + 1) % activePlayers.size();
                            Player currentOddWinner = activePlayers.get(position);
                            if (winners.containsKey(currentOddWinner))
                            {
                                winners.put(currentOddWinner, winners.get(currentOddWinner) + 1);
                            }
                        }
                    }
                    pot.reset();
                }
            }
        }
        int totalWin = 0;
        for (Player winner : winners.keySet())
        {
            totalWin += winners.get(winner);
        }
        if (totalWin > totalPot)
        {
            throw new IllegalStateException("Si sono generati soldi dal nulla!");
        }
        for (Player winner : winners.keySet())
        {
            System.out.println("[TEST] " + winner.toString() + " vince " + winners.get(winner));
            winner.win(winners.get(winner));      
            notifyPlayersUpdated(players);
        }
    }

    private Map<Hand, List<Player>> getRanking()
    {
        for (Player player : activePlayers)
        {
            player.setCurrentHand(HandEvaluator.evaluate(player, board.getCommunityCards()));
        }
        notifyPlayersUpdated(players);
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
    
    public int getTotalPot()
    {
        int total = 0;
        for (Pot pot : pots)
        {
            total+=pot.getValue();
        }
        return total;
    }
    
    private void addToPot(int amount)
    {
        for (Pot pot : pots)
        {
            if (!pot.hasMember(currentPlayer))
            {
                int currentPotBet = pot.getBet();
                if (amount >= currentPotBet)
                {
                    pot.addMember(currentPlayer);
                    amount -= currentPotBet;
                }
                else
                {
                    pots.add(pot.getSidePot(amount, currentPlayer));
                    amount = 0;
                }
            }
            if (amount == 0)
            {
                break;
            }
        }
        if (amount > 0)
        {
            Pot pot = new Pot(amount);
            pot.addMember(currentPlayer);
            pots.add(pot);
        }
    }
        
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
     * Aggiunge un giocatore controllando che non si utilizzi un nome 
     * già scelto da un altro utente
     * @param player il giocatore
     */
    public void addPlayer(Player player) {
        boolean presence = false;
        for (Player giocatore : players) {
            if (giocatore.getName().equals(player.getName())) {
                presence = true;
            }
        }
        if (presence == false) {
            players.add(player);
            player.setId(players.size());
            player.setStake(settings.getStartingStake());
        } else {
            throw new InvalidPlayerNameException("Nome già utilizzato!");
        }
    }

    /**
     * Restituisce i players
     * @return la lista dei players
     */
    public List<Player> getGiocatori() {
        return players;
    }

    public GameType getSettings() {
        return settings;
    }

    /**
     * Ritorna se sono o no presenti i players
     * @return true se sono presenti players, false altrimenti
     */
    public boolean hasPlayers() {
        return !players.isEmpty();
    }
}