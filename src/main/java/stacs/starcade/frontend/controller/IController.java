package stacs.starcade.frontend.controller;

import stacs.starcade.shared.ICard;

public interface IController {

    /**
     * Registers the client with the server and gets a unique player id.
     */
    void register();

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
