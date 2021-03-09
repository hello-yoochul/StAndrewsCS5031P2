package stacs.starcade.backend.api;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import stacs.starcade.backend.impl.IPlayer;
import stacs.starcade.backend.impl.*;
import stacs.starcade.backend.impl.Player;
import stacs.starcade.shared.Checks;
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
    @GetMapping("/registerPlayer/{playerName}")
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
    @GetMapping("/nextRound/{playerID}")
    public ArrayList<ICard> startNextRound(@PathVariable int playerID)  {
        final IPlayer player = model.getPlayer(playerID);

        ArrayList<ICard> twelveCards = model.getTwelveCards();
        player.startRound(twelveCards);

        return twelveCards;
    }

    /**
     * Ends round player with ID playerID is currently playing.
     *
     * This will stop the timer for the current round.
     *
     * @param playerID the unique player ID
     */
    @PostMapping( value = {"/endRound/{playerID}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void endRound(@PathVariable int playerID, @RequestBody ArrayList<ArrayList<ICard>> sets) {
        final IPlayer player = model.getPlayer(playerID);

        for (ArrayList<ICard> cardList : sets) {
            for (ICard card : cardList) {
                if (!player.getStoredCards().contains(card)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stale cards.");
                }
            }

            if (!Checks.isSet((ICard[]) cardList.toArray())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not a set.");
            }

        }

        player.endRound();
        model.getLeaderboard().sortList();

    }

    /**
     * Removes player from leaderboard.
     *
     * @param playerID ID of player that is removed
     */
    @GetMapping("/disconnect/{playerID}")
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
