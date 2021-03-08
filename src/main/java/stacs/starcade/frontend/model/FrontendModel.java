package stacs.starcade.frontend.model;


import stacs.starcade.backend.impl.ILeaderBoard;
import stacs.starcade.backend.impl.LeaderBoard;
import stacs.starcade.frontend.view.main.FrontendView;
import stacs.starcade.shared.ICard;
import stacs.starcade.shared.ITimer;
import stacs.starcade.shared.Timer;

import java.time.Duration;
import java.util.*;

/**
 * All the data is stored here such as client choice of cards.
 * Once the data is updated, the {@link FrontendView} will be
 * invoked to repaint the panel.
 */
public class FrontendModel extends Observable implements IFrontendModel {
    public final static int MAXIMUM_NUMBER_OF_SELECTED_CARDS = 3;

    private GameStatus status;
    private List<ICard> cardsOnBoard;
    private List<ICard> selectedCards;
    private int playerId;
    private ArrayList<ICard[]> setsLog;
    private String[][] leaderBoard;
    private String playerName;
    private ITimer timer;

    /**
     * Construct FrontendModel.
     */
    public FrontendModel() {
        cardsOnBoard = new ArrayList<>();
        selectedCards = new ArrayList<>();
        setsLog = new ArrayList<>();
//        leaderBoard = new LeaderBoard();
        timer = new Timer();
        status = GameStatus.PAUSED;
    }

    public void startTimer() {
        timer.start();
        update();
    }

    public Duration getTime() {
        return timer.getTime();
    }

    /**
     * Notify observers.
     */
    @Override
    public void update() {
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Set the leader board.
     *
     * @param leaderBoard the leader board.
     */
    @Override
    public void setLeaderBoard(String[][] leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    /**
     * Get the leader board.
     *
     * @return the leader board.
     */
    @Override
    public String[][] getLeaderBoard() {
        return this.leaderBoard;
    }

    /**
     * set up card when client start the game or 3 set card are spotted.
     *
     * @param cards the initial 12 cards
     */
    @Override
    public void setUpCard(List<ICard> cards) {
        this.cardsOnBoard = cards;
        update();
    }

    /**
     * Get the cards on the current board.
     *
     * @return the cards on the board
     */
    @Override
    public List<ICard> getCards() {
        return cardsOnBoard;
    }

    /**
     * Set game status: RUNNING or PAUSED.
     *
     * @param status one of the {@link GameStatus}
     */
    @Override
    public void setGameStatus(GameStatus status) {
        this.status = status;
    }

    /**
     * Get the {@link GameStatus}.
     *
     * @return the game status
     */
    @Override
    public GameStatus getStatus() {
        return status;
    }

    /**
     * Set the unique player id.
     *
     * @param playerId the unique player id
     */
    @Override
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Get the unique player id.
     *
     * @return the unique player id.
     */
    @Override
    public Integer getPlayerId() {
        return playerId;
    }

    @Override
    public void removeSelectedCard(ICard card) { selectedCards.remove(card); }

    /**
     * Select card among the cards on board.
     *
     * @param card selected Card among the cards on board
     */
    @Override
    public void selectCard(ICard card) {
        if (!cardsOnBoard.contains(card)) {
            throw new IllegalArgumentException("card does not exsit on the board");
        }
        selectedCards.add(card);
    }

    /**
     * Get the selected cards.
     *
     * @return the selected cards
     */
    @Override
    public List<ICard> getSelectedCards() {
        return selectedCards;
    }

    /**
     * Adds a valid set to the setsLog of current round
     */
    @Override
    public void setSetsLog(ICard[] threeCards) {
        setsLog.add(threeCards);
        System.out.println("SET SETS LOG - SIZE: " + setsLog.size());
        for (ICard card : threeCards) {
            selectedCards.remove(card);
        }
        update();
    }

    /**
     * Gets log of sets that have been logged in current round.
     *
     * @return setsLog object
     */
    @Override
    public ArrayList<ICard[]> getSetsLog() {
        return this.setsLog;
    }

    /**
     * Set the player name.
     *
     * @param playerName the player name
     */
    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Get the player name.
     *
     * @return the current player name.
     */
    @Override
    public String getPlayerName() {
        return playerName;
    }
}
