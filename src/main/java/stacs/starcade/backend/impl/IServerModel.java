package stacs.starcade.backend.impl;

import stacs.starcade.shared.ICard;

import java.util.ArrayList;

public interface IServerModel {

    /**
     * Gets player with given playerID.
     * @param playerID given ID
     * @return IPlayer object that has ID playerID
     */
    IPlayer getPlayer(int playerID);

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

    void addPlayer(IPlayer newP);

    void disconnectPlayer(IPlayer removedPlayer);
}
