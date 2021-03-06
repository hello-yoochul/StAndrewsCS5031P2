package stacs.starcade.backend.api;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import stacs.starcade.backend.model.ICard;
import stacs.starcade.backend.model.ILeaderBoard;

/**
 * Interface for Backend API for Set Game.
 */
public interface ISetGameAPI {

    /**
     * Get method for leaderboard.
     *
     * @return a list of individual leaderboard entries
     */
    List<ILeaderBoard> getLeaderBoard();

    /**
     * Post method to start a new game.
     *
     * @return an int representing the unique player ID
     */
    Integer startGame();

    /**
     * Post method to get current player's cards.
     *
     * @param playerID the unique player ID
     * @return the cards that the current player has
     */
    List<ICard> getCards(@PathVariable int playerID);


    /**
     * Check if the three cards are Set.
     *
     * @param firstCard  the first card properties
     * @param secondCard the first card properties
     * @param thirdCard  the first card properties
     * @return true if it is set
     */
    boolean isSet(@RequestParam int firstCard, @RequestParam int secondCard, @RequestParam int thirdCard);

}
