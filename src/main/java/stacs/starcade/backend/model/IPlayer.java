package stacs.starcade.backend.model;

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
}
