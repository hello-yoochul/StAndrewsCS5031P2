package stacs.starcade.frontend.controller;

import stacs.starcade.shared.ICard;

public interface IController {
    /**
     * Start a game.
     */
    void startGame();

    /**
     * Set up the 12 cards.
     */
    void setUpCards();

//    /**
//     * Check if the three cards are set.
//     *
//     * @return true if it is set
//     */
//    boolean isSet();

    /**
     * Pause the game.
     */
    void pauseGame();

    /**
     * Resume the game.
     */
    void resumeGame();

    /**
     * Select a card. Player will invoke this method 3 times to choose three cards.
     *
     * @param card a card to check if chosen cards are set
     */
    void selectCard(ICard card);

    /**
     * Set the current unique player id.
     */
    void setPlayerId();

    void validateCards(ICard[] threeCards);
}
