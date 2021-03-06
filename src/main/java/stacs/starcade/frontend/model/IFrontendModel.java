package stacs.starcade.frontend.model;

import java.util.List;

public interface IFrontendModel {
    enum GameStatus {RUNNING, PAUSED}

    /**
     * Notify observers.
     */
    void update();

    /**
     * set up card when client start the game or 3 set card are spotted.
     *
     * @param cards the initial 12 cards
     */
    void setUpCard(List<ICard> cards);

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
     * Select Card among the cards on board.
     *
     * @param card selected Card among the cards on board
     */
    void selectCard(ICard card);
}
