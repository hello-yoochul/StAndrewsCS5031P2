package stacs.starcade.backend.model;

import java.time.Duration;

/**
 * Interface for the representation of an entry in the player leaderboard.
 */
public interface ILeaderBoard {

    /**
     * Getter for the playerID.
     * @return a unique ID representing a player
     */
    int getPlayerId();

    /**
     * Getter for the player name.
     * @return a String representing the player name
     */
    String getPlayerName();

    /**
     * Getter for the player's play time.
     * @return a duration that represents the time it took to solve the problem
     */
    Duration getPlayerTime();

}
