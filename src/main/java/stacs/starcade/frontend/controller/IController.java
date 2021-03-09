package stacs.starcade.frontend.controller;

import java.util.ArrayList;
import stacs.starcade.shared.ICard;

public interface IController {

    /**
     * Registers the client with the server and gets a unique player id.
     */
    void register();

    /**
     * Continuously polls for updates on leaderboard.
     */
    void pollForLeaderBoard();

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
    void endRound(ArrayList<ICard[]> sets);

    /**
     * Trigger disconnecting client from server.
     */
    void disconnect();
}
