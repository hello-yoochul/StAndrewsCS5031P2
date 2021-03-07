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
import stacs.starcade.backend.impl.Model;
import stacs.starcade.backend.impl.Player;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.ICard;
import stacs.starcade.backend.impl.ILeaderBoard;
import stacs.starcade.backend.impl.IModel;

/**
 * Spring Backend API for Set Game.
 */
@RestController
public class SetGameAPI implements ISetGameAPI {
    private ArrayList<IPlayer> players = new ArrayList<>();
    private Map<IPlayer, IModel> games = new HashMap<>();

    /**
     * Get method for leaderboard.
     *
     * @return a list of individual leaderboard entries
     */
    @GetMapping("/getLeaderboard")
    public List<ILeaderBoard> getLeaderBoard() {
        return null;
    }

//    /**
//     * Post method to start a new game.
//     *
//     * @return an int representing the unique player ID
//     */
//    @GetMapping("/startGame")
//    public Integer startGame() {
//        int newPID;
//
//        if (players.isEmpty()){
//           newPID = 1;
//        } else {
//            newPID = players.get(players.size() - 1).getPlayerId() + 1;
//        }
//
//        IPlayer newPlayer = new Player(newPID);
//        players.add(newPlayer);
//        IModel model = new Model();
//        games.put(newPlayer, model);
//        return newPlayer.getPlayerId();
//    }

    /**
     * Post method to start a new game.
     *
     * @return an int representing the unique player ID
     */
    @GetMapping("/startGame")
    public Integer startGame() {
        int newPlayerID = ;
        // Get next available playerID
        // Create player instance using generated pID and given pName
        // Call Model method to add player to game
        // return player ID
    }

    /**
     * Post method to get current player's cards.
     *
     * @param playerID the unique player ID
     * @return the cards that the current player has
     */
    @GetMapping("/getCards/{playerID}")
    public List<ICard> getCards(@PathVariable int playerID) {
        for (IPlayer p : players){
            if (p.getPlayerId() == playerID) {
                return games.get(p).getTwelveCards();
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player not found");

    }
    
}
