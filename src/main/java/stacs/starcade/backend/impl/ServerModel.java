package stacs.starcade.backend.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import stacs.starcade.shared.Card;
import stacs.starcade.shared.Checks;
import stacs.starcade.shared.ICard;

import java.util.ArrayList;

public class ServerModel implements IServerModel {
    private int nextPlayerID = 0;
    private ILeaderBoard leaderBoard;

    private ArrayList<ICard> allCards;

    private final static int NUM_TWELVE = 12;
    public final static int NUM_SETS = 5;
    public final static int CARD_NUM_IN_SET = 3;

    public ServerModel() {
        setCards();
        leaderBoard = new LeaderBoard();
    }

    /**
     * Generates an array list of 81 card objects with unique attribute-expression combinations.
     */
    private void setCards() {
        allCards = new ArrayList<>();

        for (ICard.Colour i : ICard.Colour.values()) {
            for (ICard.Shape j : ICard.Shape.values()) {
                for (ICard.Number k : ICard.Number.values()) {
                    for (ICard.LineStyle l : ICard.LineStyle.values()) {
                        ICard card = new Card();
                        card.setColour(i);
                        card.setShape(j);
                        card.setNumber(k);
                        card.setLineStyle(l);
                        allCards.add(card);
                    }
                }
            }
        }

    }

    /**
     * Gets player with given playerID.
     *
     * @param playerID given ID
     * @return IPlayer object that has ID playerID
     */
    @Override
    public IPlayer getPlayer(int playerID) {
        for (IPlayer p : this.getLeaderboard().getPlayersList()) {
            if (p.getPlayerId() == playerID) {
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player not found");
    }

    /**
     * Generates a unique player ID.
     *
     * @return player ID as int
     */
    @Override
    public int generatePlayerID() {
        return ++this.nextPlayerID;
    }

    /**
     * Gets the leaderboard of all players on the server.
     *
     * @return a ILeaderBoard object
     */
    @Override
    public ILeaderBoard getLeaderboard() {
        return this.leaderBoard;
    }

    /**
     * Generates random 12 cards that make 5 sets.
     * @return array list of 12 cards
     */
    public ArrayList<ICard> getTwelveCards() {
        ArrayList<ICard> twelveCards;

        while (true) {
            int numSets = 0;
            twelveCards = new ArrayList<>();

            for (int i = 0; i < NUM_TWELVE; i++) {
                twelveCards.add(getRandomCardNotInList(twelveCards));
            }

            for (int i = 0; i < twelveCards.size() - 2; i++) {
                for (int j = i; j < twelveCards.size() - 1; j++){
                    if (j == i){
                        continue;
                    }

                    for (int k = j; k < twelveCards.size(); k++){
                        if (k == j){
                            continue;
                        }

                        if (Checks.isSet(new ICard[]{
                                twelveCards.get(i),
                                twelveCards.get(j),
                                twelveCards.get(k)
                        })) {
                            numSets ++;

                            if (numSets > 3){
                                break;
                            }
                        }
                    }
                }
            }

            if (numSets == NUM_SETS) {
                return twelveCards;
            }

        }
    }

    /**
     * Adds a player to the leaderboard once connected.
     * @param newP conencting player
     */
    @Override
    public void addPlayer(IPlayer newP) {
        this.leaderBoard.addPlayer(newP);
    }

    /**
     * Disconnects player by removing him from leaderboard.
     * @param removedPlayer
     */
    @Override
    public void disconnectPlayer(IPlayer removedPlayer) {
        this.leaderBoard.removePlayer(removedPlayer);
    }

    /**
     * Generates a random card.
     * @return the random card.
     */
    private ICard getRandomCard(){
        return allCards.get((int) (Math.random() * allCards.size()));
    }

    /**
     * Gets a random card that is not included in the given list.
     * @param list given list of cards
     * @return generated card
     */
    private ICard getRandomCardNotInList(ArrayList<ICard> list) {
        while (true) {
            ICard c = getRandomCard();
            boolean unique = true;
            for (ICard card : list) {
                if (c.equals(card)) {
                    unique = false;
                    break;
                }
            }

            if (unique) {
                return c;
            }
        }
    }


}
