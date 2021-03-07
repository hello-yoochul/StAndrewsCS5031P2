package stacs.starcade.backend.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;
import stacs.starcade.backend.impl.IPlayer;
import stacs.starcade.backend.impl.*;
import stacs.starcade.shared.Card;
import stacs.starcade.backend.impl.Player;
import stacs.starcade.shared.ICard;

/**
 * Spring Backend API for Set Game.
 */
@RestController
public class SetGameAPI implements ISetGameAPI {

    private IModel model = new Model();

    /**
     * Gets the leaderboard containing player objects.
     *
     * @return a list of individual leaderboard entries
     */
    @GetMapping("/getLeaderboard")
    public List<IPlayer> getLeaderBoard() {
        return model.getLeaderboard().getPlayersList();
    }


    /**
     * Registers a new player with a given playerName and generates a unique player ID.
     *
     * Creates a player instance and passes it to the model, from where it is added to the leaderboard.
     *
     * @return newly generated player ID.
     */
    @GetMapping("/registerPlayer")
    public Integer registerPlayer(@PathVariable String playerName) {
        int newPID = model.getPlayerID();
        IPlayer newP = new Player(playerName, newPID);

        model.addPlayer(newP);

        return newPID;
    }

    /**
     * Triggers the start of a new round for player with ID playerID.
     *
     * @return returns an array with twelve cards that will be used for the new round.
     */
    @GetMapping("/nextRound")
    public ArrayList<ICard> startNextRound(@PathVariable int playerID) {

        ILeaderBoard lB = model.getLeaderboard();
        for (IPlayer p : lB.getPlayersList()) {
            if (p.getPlayerId() == playerID) {
                ArrayList<ICard> twelveCards = model.getTwelveCards();
                p.startRound(twelveCards);
                return twelveCards;
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player not found");
    }

    /**
     * Ends round player with ID playerID is currently playing.
     *
     * This will stop the timer for the current round.
     *
     * @param playerID
     */
    // TODO: Pass in sets and verify them
    @PostMapping("/endRound")
    public void endRound(@PathVariable int playerID) {

        ILeaderBoard lB = model.getLeaderboard();
        for (IPlayer p : lB.getPlayersList()) {
            if (p.getPlayerId() == playerID) {
                p.endRound();
                break;
            }
        }
    }
}
