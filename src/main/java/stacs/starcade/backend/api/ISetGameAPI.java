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
     * Get method for leaderboard.
     *
     * @return a list of individual leaderboard entries
     */
    List<IPlayer> getLeaderBoard();

    /**
     * Post method to start a new game.
     *
     * @return an int representing the unique player ID
     */
    Integer registerPlayer(@PathVariable String playerName);

    ArrayList<ICard> startNextRound(@PathVariable int playerID);

    void endRound(@PathVariable int playerID);

}
