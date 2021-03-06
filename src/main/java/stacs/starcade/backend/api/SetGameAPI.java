package stacs.starcade.backend.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import stacs.starcade.backend.impl.Model;
import stacs.starcade.backend.model.ICard;
import stacs.starcade.backend.model.ILeaderBoard;

/**
 * Spring Backend API for Set Game.
 */
@RestController
public class SetGameAPI implements ISetGameAPI {
    // in-memory representation of the game data, will be replaced with
    // storage in a database at another time
    private int gameId = 0;
    private Map<Integer, Model> games = new HashMap<>();

    /**
     * Get method for leaderboard.
     *
     * @return a list of individual leaderboard entries
     */
    @GetMapping("/getLeaderboard")
    public List<ILeaderBoard> getLeaderBoard() {
        return null;
    }

    /**
     * Post method to start a new game.
     *
     * @return an int representing the unique player ID
     */
    @PostMapping("/startGame")
    public Integer startGame() {
        return null;
    }

    /**
     * Post method to get current player's cards.
     *
     * @param playerID the unique player ID
     * @return the cards that the current player has
     */
    @PostMapping("/getCards")
    public List<ICard> getCards(int playerID) {
        return null;
    }

    /**
     * Check if the three cards are Set.
     *
     * @param firstCard  the first card properties
     * @param secondCard the first card properties
     * @param thirdCard  the first card properties
     * @return true if it is set
     */
    @Override
    public boolean isSet(int firstCard, int secondCard, int thirdCard) {
        return false;
    }


}
