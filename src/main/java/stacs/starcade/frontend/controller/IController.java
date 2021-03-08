package stacs.starcade.frontend.controller;

import stacs.starcade.shared.ICard;

public interface IController {

    /**
     * Gets the leaderboard from the model.
     */
    void getLeaderBoard();

    /**
     * Start a game.
     */
    void startGame();

    /**
     * Set up the 12 cards.
     */
    void setUpCards();

    /**
     * Select a card. Player will invoke this method 3 times to choose three cards.
     *
     * @param card a card to check if selected cards are set
     */
    void selectCard(ICard card);

    /**
     * Validate the three cards if it is set.
     *
     * @param threeCards the three cards to be validated
     */
    void validateCards(ICard[] threeCards);

    /**
     * End the round.
     */
    void endRound();

    /**
     * Trigger disconnecting client from server.
     */
    void disconnect();
}
