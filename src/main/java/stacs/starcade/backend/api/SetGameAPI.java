package stacs.starcade.backend.api;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stacs.starcade.backend.impl.IPlayer;
import stacs.starcade.backend.impl.*;
import stacs.starcade.backend.impl.Player;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.ICard;

/**
 * Spring Backend API for Set Game.
 */
@RestController
public class SetGameAPI implements ISetGameAPI {

    private IModel model = new Model();

    /**
     * Registers a new player with a given playerName and generates a unique player ID.
     *
     * Creates a player instance and passes it to the model, from where it is added to the leaderboard.
     *
     * @return newly generated player ID.
     */
    @PostMapping("/registerPlayer/{playerName}")
    public Integer registerPlayer(@PathVariable String playerName) {
        int newPID = model.generatePlayerID();
        IPlayer newP = new Player(playerName, newPID);
        model.addPlayer(newP);
        return newPID;
    }

    /**
     * Triggers the start of a new round for player with ID playerID.
     *
     * @return returns an array with twelve cards that will be used for the new round.
     */
    @PostMapping("/nextRound/{playerID}")
    public ArrayList<ICard> startNextRound(@PathVariable int playerID) throws ResponseStatusException {
//        ArrayList<ICard> twelveCards = model.getTwelveCards();
//        model.getPlayer(playerID).startRound(twelveCards);
        ArrayList<ICard> twelveCards = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            ICard card = new Card();
            card.setLineStyle(ICard.LineStyle.DOTTED);
            card.setColour(ICard.Colour.RED);
            card.setShape(ICard.Shape.TRIANGLE);
            card.setNumber(ICard.Number.ONE);
            twelveCards.add(card);
        }
        return twelveCards;
    }

    /**
     * Ends round player with ID playerID is currently playing.
     *
     * This will stop the timer for the current round.
     *
     * @param playerID the unique player ID
     */
    // TODO: Pass in sets and verify them
    @PostMapping("/endRound/{playerID}")
    public void endRound(@PathVariable int playerID) throws ResponseStatusException {
        model.getPlayer(playerID).endRound();
    }

    /**
     * Removes player from leaderboard.
     * @param playerID ID of player that is removed
     */
    @PostMapping("/disconnect/{playerID}")
    public void disconnect(@PathVariable int playerID) {
        IPlayer removedPlayer = model.getPlayer(playerID);
        model.disconnectPlayer(removedPlayer);
    }

    /**
     * Gets the leaderboard containing player objects.
     *
     * @return a list of individual leaderboard entries
     */
    @GetMapping("/getLeaderboard")
    public ArrayList<IPlayer> getLeaderBoard() {
        return model.getLeaderboard().getPlayersList();
    }
}
