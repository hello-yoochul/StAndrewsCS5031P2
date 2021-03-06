package stacs.starcade.backend.impl;

import stacs.starcade.backend.model.ICard;
import stacs.starcade.backend.model.IModel;
import stacs.starcade.backend.model.IPlayer;

import java.util.ArrayList;

public class Model implements IModel {

    private ArrayList<IPlayer> players;
    private ICard[] cards;

    public Model() {
        setCards();
    }

    /**
     * Generates an array list of 81 card objects with unique attribute-expression combinations.
     */
    public void setCards() {
        ArrayList<ICard> allCards = new ArrayList<>();

        for (ICard.Colour i : ICard.Colour.values()) {
            for (ICard.Shape j : ICard.Shape.values()) {
                for (ICard.Number k : ICard.Number.values()) {
                    for (ICard.LineType l : ICard.LineType.values()) {

                        ICard card = new Card();
                        card.setColour(i);
                        card.setShape(j);
                        card.setNumber(k);
                        card.setLineType(l);
                        allCards.add(card);

                    }
                }
            }
        }

        this.cards = (ICard[]) allCards.toArray();
    }

    public ICard[] getCards() { return this.cards; }

    @Override
    public void logSet(ICard[] threeCards) {
        IPlayer cardsOwner = threeCards[0].getOwner();
        cardsOwner.setSetsLog(threeCards);
    }
}
