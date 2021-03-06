package stacs.starcade.frontend.model;


import java.util.*;

/**
 * This class is one part of panels on main swing frame and
 * it extends Observable to notify the observer(the view).
 *
 * @author 200012756
 */
public class FrontendModel extends Observable {
    public enum GameStatus {RUNNING, PAUSED}

    GameStatus status;

    List<Card> cardsOnDeck;

    List<Card> chosenCards;

    int playerId;

    /**
     * Construct CanvasModel with initializing the shapeList and undoList.
     */
    public FrontendModel() {
        cardsOnDeck = new ArrayList<>();
        chosenCards = new ArrayList<>();
    }


    public void setUpCard(List<Card> cards) {
        this.cardsOnDeck = cards;
    }

    /**
     * Notify observers.
     */
    public void update() {
        this.setChanged();
        this.notifyObservers();
    }

    public void setGameStatus(GameStatus status) {
        this.status = status;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void chooseCard(Card card) {
        chosenCards.add(card);
    }
}
