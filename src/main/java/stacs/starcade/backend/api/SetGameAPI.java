package stacs.starcade.backend.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import stacs.starcade.backend.impl.Model;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.ICard;
import stacs.starcade.backend.impl.ILeaderBoard;
import stacs.starcade.backend.impl.IModel;

import static stacs.starcade.shared.ICard.Colour.*;
import static stacs.starcade.shared.ICard.Number.*;
import static stacs.starcade.shared.ICard.LineStyle.*;
import static stacs.starcade.shared.ICard.Shape.*;


/**
 * Spring Backend API for Set Game.
 */
@RestController
public class SetGameAPI implements ISetGameAPI {
    // in-memory representation of the game data, will be replaced with
    // storage in a database at another time
    private int gameId = 0;
    private Map<Integer, IModel> games = new HashMap<>();

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
        IModel model = new Model();
        games.put(++gameId, model);
        return gameId;
    }

    /**
     * Post method to get current player's cards.
     *
     * @param playerID the unique player ID
     * @return the cards that the current player has
     */
    @PostMapping("/getCards/{playerID}")
    public List<ICard> getCards(@PathVariable int playerID) {
        List<ICard> cards = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            ICard card = new Card();
            card.setNumber(ONE);
            card.setColour(RED);
            card.setLineStyle(DOTTED);
            card.setShape(TRIANGLE);
            cards.add(card);
        }

        return cards;
    }

//    /**
//     * Check if the three cards are Set.
//     *
//     * @param firstCard  the first card properties
//     * @param secondCard the first card properties
//     * @param thirdCard  the first card properties
//     * @return true if it is set
//     */
//    @PostMapping("/isSet")
//    public boolean isSet(@RequestParam int firstCard, @RequestParam int secondCard, @RequestParam int thirdCard) {
//        return false;
//    }
}
