package stacs.starcade.frontend.model;


import java.util.*;

/**
 *
 */
public class FrontendModel extends Observable implements IFrontendModel {
    private IFrontendModel.GameStatus status;
    private List<ICard> cardsOnDeck;
    private List<ICard> chosenCards;
    private int playerId;

    /**
     * Construct FrontendModel.
     */
    public FrontendModel() {
        cardsOnDeck = new ArrayList<>();
        chosenCards = new ArrayList<>();
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
     * set up card when client start the game or 3 set card are spotted.
     *
     * @param cards the initial 12 cards
     */
    @Override
    public void setUpCard(List<ICard> cards) {
        this.cardsOnDeck = cards;
    }

    /**
     * Get the cards on the current board.
     *
     * @return the cards on the board
     */
    @Override
    public List<ICard> getCards() {
        return null;
    }

    /**
     * Set game status: RUNNING or PAUSED.
     *
     * @param status one of the {@link GameStatus}
     */
    @Override
    public void setGameStatus(IFrontendModel.GameStatus status) {
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
        return null;
    }

    /**
     * Select Card among the cards on board.
     *
     * @param card selected Card among the cards on board
     */
    @Override
    public void selectCard(ICard card) {
        chosenCards.add(card);
    }

    /**
     * Get the chosen cards.
     *
     * @return the chosen cards
     */
    @Override
    public List<ICard> getChosenCards() {
        return null;
    }
}
