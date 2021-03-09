package stacs.starcade.frontend.model;

import stacs.starcade.frontend.view.main.FrontendView;
import stacs.starcade.shared.ICard;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * All the data is stored here such as client choice of cards.
 * Once the data is updated, the {@link FrontendView} will be
 * invoked to repaint the panel.
 */
public interface IClientModel {
    /**
     * Status of game.
     */
    enum GameStatus {RUNNING, PAUSED}

    /**
     * Notify observers.
     */
    void update();

    /**
     * Start the timer.
     */
    void startTimer();

    /**
     * Get the duration between start time and end time.
     *
     * @return the duration between start time and end time.
     */
    Duration getTime();

    /**
     * Reset the timer.
     */
    public void resetTimer();

    /**
     * Set the leader board.
     *
     * @param leaderBoard the leader board.
     */
    void setLeaderBoard(String[][] leaderBoard);

    /**
     * Get the leader board.
     *
     * @return the leader board.
     */
    String[][] getLeaderBoard();

    /**
     * Set up card when client start the game or 3 set card are spotted.
     *
     * @param cards the initial 12 cards
     */
    void setUpCard(List<ICard> cards);

    /**
     * Get the cards on the current board.
     *
     * @return the cards on the board
     */
    List<ICard> getCards();

    /**
     * Set game status: RUNNING or PAUSED.
     *
     * @param status one of the {@link GameStatus}
     */
    void setGameStatus(GameStatus status);

    /**
     * Get the {@link GameStatus}.
     *
     * @return the game status
     */
    GameStatus getStatus();

    /**
     * Set the unique player id.
     *
     * @param playerId the unique player id
     */
    void setPlayerId(int playerId);

    /**
     * Get the unique player id.
     *
     * @return the unique player id.
     */
    Integer getPlayerId();

    /**
     * Remove selected card.
     *
     * @param card selected Card
     */
    void removeSelectedCard(ICard card);

    /**
     * Select card among the cards on board.
     *
     * @param card selected Card among the cards on board
     */
    void selectCard(ICard card);

    /**
     * Get the selected cards.
     *
     * @return the selected cards
     */
    List<ICard> getSelectedCards();

    /**
     * Adds a valid set to the setsLog of current round
     *
     * @param threeCards may be a valid set
     */
    void setSetsLog(ICard[] threeCards);

    /**
     * Gets log of sets that have been logged in current round.
     *
     * @return setsLog object
     */
    ArrayList<ICard[]> getSetsLog();

    /**
     * Set the player name.
     *
     * @param playerName the player name
     */
    void setPlayerName(String playerName);

    /**
     * Get the player name.
     *
     * @return the current player name.
     */
    String getPlayerName();
}
