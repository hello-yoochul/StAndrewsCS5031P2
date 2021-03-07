package stacs.starcade.backend.model;

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

    void setSetsLog(ICard[] threeCards);

    ArrayList<ICard[]> getSetsLog();
}
