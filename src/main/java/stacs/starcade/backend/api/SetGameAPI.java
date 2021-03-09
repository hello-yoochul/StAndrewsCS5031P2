package stacs.starcade.backend.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import stacs.starcade.backend.impl.IPlayer;
import stacs.starcade.backend.impl.*;
import stacs.starcade.backend.impl.Player;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.Checks;
import stacs.starcade.shared.ICard;

import static stacs.starcade.backend.impl.ServerModel.CARD_NUM_IN_SET;

/**
 * Spring Backend API for Set Game.
 */
@RestController
public class SetGameAPI implements ISetGameAPI {

    private IServerModel model = new ServerModel();

    /**
     * Registers a new player with a given playerName and generates a unique player ID.
     * <p>
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
    public ArrayList<ICard> startNextRound(@PathVariable int playerID) {
        final IPlayer player = model.getPlayer(playerID);

        ArrayList<ICard> twelveCards = model.getTwelveCards();
        player.startRound(twelveCards);

        return twelveCards;
    }

    /**
     * Ends round player with ID playerID is currently playing.
     * <p>
     * This will stop the timer for the current round.
     *
     * @param playerID the unique player ID
     */
    @JsonSubTypes({
            @JsonSubTypes.Type(value = ICard.class),
    })
    @PostMapping(value = {"/endRound/{playerID}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void endRound(@PathVariable int playerID, @RequestBody List<List<Card>> sets) {
        final IPlayer player = model.getPlayer(playerID);

        for (List<Card> cardList : sets) {
            for (ICard card : cardList) {
                if (!player.getStoredCards().contains(card)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stale cards.");
                }
            }

            if (!Checks.isSet((ICard[]) cardList.toArray(new ICard[CARD_NUM_IN_SET]))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not a set.");
            }

            System.out.println("SUCCESS");
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

