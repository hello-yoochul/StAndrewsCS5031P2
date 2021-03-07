package stacs.starcade.backend.impl;

import java.time.Duration;
import java.util.List;

/**
 * Interface for the representation of an entry in the player leaderboard.
 */
public interface ILeaderBoard {

    /**
     * Adds player to leaderboard.
     *
     * @param player player object that shall be added
     */
    void addPlayer(IPlayer player);

    /**
     * Removes player from leaderboard once player has disconnected.
     *
     * @param playerID id of player that shall be removed
     */
    void removePlayer(int playerID);

    /**
     * Gets list of players that are currently playing.
     * Player objects contain information on individual performance: num of rounds played and average time per round.
     * @return
     */
    List<IPlayer> getPlayersList();
}
