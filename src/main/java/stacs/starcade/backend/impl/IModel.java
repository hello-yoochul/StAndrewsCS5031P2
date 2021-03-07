package stacs.starcade.backend.impl;

import stacs.starcade.shared.ICard;

import java.util.ArrayList;

public interface IModel {

    /**
     * Generates a unique player ID.
     * @return player ID as int
     */
    int generatePlayerID();

    /**
     * Gets the leaderboard of all players on the server.
     * @return a ILeaderBoard object
     */
    ILeaderBoard getLeaderboard();

    /**
     * Gets twelve cards with 5 sets.
     * Persists the cards, if no set of cards exists, create new set
     * @return ArrayList of twelve cards
     */
    ArrayList<ICard> getTwelveCards();
}
