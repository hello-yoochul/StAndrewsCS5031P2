package stacs.starcade.frontend.model;


import java.util.*;

/**
 *
 */
public class FrontendModel extends Observable implements IFrontendModel {
    private IFrontendModel.GameStatus status;
    private List<ICard> cardsOnBoard;
    private List<ICard> chosenCards;
    private int playerId;

    /**
     * Construct FrontendModel.
     */
    public FrontendModel() {
        cardsOnBoard = new ArrayList<>();
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
        this.cardsOnBoard = cards;
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
        return playerId;
    }


    public static void main(String[] args) {
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(random.nextInt(10));
//        }

        List<String> s= new ArrayList<>();
        s.add("Dfd");

        System.out.println(s.get(0));
    }
    /**
     * Select card among the cards on board.
     *
     * @param card selected Card among the cards on board
     */
    @Override
    public void selectCard(ICard card) {
        if(!cardsOnBoard.contains(card)){
            throw new IllegalArgumentException("card does not exsit on the board");
        }
        cardsOnBoard.remove(card);
        chosenCards.add(card);
    }

    /**
     * Get the chosen cards.
     *
     * @return the chosen cards
     */
    @Override
    public List<ICard> getChosenCards() {
        return chosenCards;
    }
}
