package stacs.starcade.backend.impl;

import stacs.starcade.shared.Card;
import stacs.starcade.shared.Checks;
import stacs.starcade.shared.ICard;

import java.util.ArrayList;

public class Model implements IModel {

    ArrayList<ICard> allCards;
    ArrayList<ICard> twelveCards;
    ILeaderBoard leaderBoard;
    private int newPlayerID = 0;

    private final static int NUM_TWELVE = 12;
    private final static int NUM_SETS = 5;

    public Model() {
        setCards();
        twelveCards = new ArrayList<>();
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
     * Generates a unique player ID.
     *
     * @return player ID as int
     */
    @Override
    public int generatePlayerID() {
        return ++this.newPlayerID;
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

    public ArrayList<ICard> getTwelveCards() {
        if (twelveCards.isEmpty()) {
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
                            }
                        }
                    }
                }

                if (numSets == NUM_SETS) {
                    return twelveCards;
                }

            }

        }

        return twelveCards;
    }

    private ICard getRandomCard(){
        return allCards.get((int) (Math.random() * allCards.size()));
    }

    private ICard getRandomCardNotInList(ArrayList<ICard> list) {
        ICard c = getRandomCard();
        boolean unique = true;
        while (true) {
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
