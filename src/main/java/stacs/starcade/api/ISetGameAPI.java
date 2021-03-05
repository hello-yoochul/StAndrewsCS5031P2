package stacs.starcade.api;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import stacs.starcade.model.ICard;
import stacs.starcade.model.ILeaderBoard;

/**
 * Interface for Backend API for Set Game.
 */
public interface ISetGameAPI {

  /**
   * Get method for leaderboard.
   * @return a list of individual leaderboard entries
   */
  List<ILeaderBoard> getLeaderBoard();

  /**
   * Post method to start a new game.
   * @return an int representing the unique player ID
   */
  Integer startGame();

  /**
   * Post method to get current player's cards.
   * @param playerID the unique player ID
   * @return the cards that the current player has
   */
  List<ICard> getCards(@PathVariable int playerID);

}
