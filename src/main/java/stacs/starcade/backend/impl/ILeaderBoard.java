package stacs.starcade.backend.impl;

import java.util.ArrayList;

/**
 * Interface for the representation of an entry in the player leaderboard.
 */
public interface ILeaderBoard {

    /**
     * Gets list of players that are currently playing.
     * Player objects contain information on individual performance: num of rounds played and average time per round.
     * @return
     */
    ArrayList<IPlayer> getPlayersList();

    /**
     * Adds player to leaderboard.
     *
     * @param player player object that shall be added
     */
    void addPlayer(IPlayer player);

    /**
     * Removes player from leaderboard once player has disconnected.
     *
     * @param removedPlayer player that shall be removed from leaderboard
     */
    void removePlayer(IPlayer removedPlayer);

    /**
     * Sorts the players list based on their average times needed to complete a round.
     * Players with lowest average time first.
     */
    void sortList();
}
