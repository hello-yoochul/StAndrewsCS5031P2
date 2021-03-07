package stacs.starcade.backend.impl;

import stacs.starcade.shared.ICard;

import java.time.Duration;
import java.util.ArrayList;

/**
 * Interface for the representation of a player.
 */
public interface IPlayer {

    /**
     * Getter for the playerID.
     * @return a unique ID representing a player
     */
    Integer getPlayerId();

    /**
     * Getter for the player name.
     * @return a String representing the player name
     */
    String getPlayerName();

    /**
     * Increments round when called.
     */
    void setRound(Duration timeLastRound);

    /**
     * Gets number of rounds player has played so far.
     * @return num of rounds as integer
     */
    Integer getRound();

    /**
     * Gets duration player has needed for previously played rounds on avergade.
     * @return average round duration
     */
    Duration getAvgTime();
}
