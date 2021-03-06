package stacs.starcade.frontend.controller;

import stacs.starcade.frontend.model.ICard;

public interface IFrontendController {
    /**
     * Start a game.
     */
    void startGame();

    /**
     * Check if the three cards are set.
     *
     * @return true if it is set
     */
    boolean isSet();

    /**
     * Pause the game.
     */
    void pauseGame();

    /**
     * Resume the game.
     */
    void resumeGame();

    /**
     * Set up the 12 cards.
     */
    void setUpCards();

    /**
     * Select a card. Player will invoke this method 3 times to choose three cards.
     *
     * @param card a card to check if chosen cards are set
     */
    void chooseCard(ICard card);
}
