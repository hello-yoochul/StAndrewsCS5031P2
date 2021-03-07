package stacs.starcade.backend.impl;

import stacs.starcade.shared.ICard;

import javax.imageio.event.IIOReadProgressListener;
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
     * Gets duration player has needed for previously played rounds on avergade.
     * @return average round duration
     */
    Duration getAvgTime();

    /**
     * Starts a new round and the timer for this round.
     *
     * @param twelveCards the cards the round shall be played with
     */
    void startRound(ArrayList<ICard> twelveCards);

    /**
     * End the current round, stopping the timer.
     */
    void endRound();
}
