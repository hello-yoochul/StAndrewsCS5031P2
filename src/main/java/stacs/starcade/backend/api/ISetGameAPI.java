package stacs.starcade.backend.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import stacs.starcade.backend.impl.IPlayer;
import stacs.starcade.shared.ICard;
import stacs.starcade.backend.impl.ILeaderBoard;

/**
 * Interface for Backend API for Set Game.
 */
public interface ISetGameAPI {


    /**
     * Registers a new player with a given playerName and generates a unique player ID.
     *
     * Creates a player instance and passes it to the model, from where it is added to the leaderboard.
     *
     * @return newly generated player ID.
     */
    Integer registerPlayer(@PathVariable String playerName);
    /**
     * Triggers the start of a new round for player with ID playerID.
     *
     * @return returns an array with twelve cards that will be used for the new round.
     */
    ArrayList<ICard> startNextRound(@PathVariable int playerID);

    /**
     * Ends round player with ID playerID is currently playing.
     *
     * This will stop the timer for the current round.
     *
     * @param playerID
     */
    void endRound(@PathVariable int playerID);

    /**
     * Removes player from leaderboard.
     * @param playerID ID of player that is removed
     */
    void disconnect(int playerID);

    /**
     * Gets the leaderboard containing player objects.
     *
     * @return a list of individual leaderboard entries
     */
    ArrayList<IPlayer> getLeaderBoard();

}
