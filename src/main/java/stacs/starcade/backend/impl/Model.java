package stacs.starcade.backend.impl;

import stacs.starcade.backend.model.ICard;
import stacs.starcade.backend.model.IModel;

import java.util.ArrayList;

public class Model implements IModel {

    private ICard[] cards;

    public Model() {
        setCards();
    }

    /**
     * Generates an array list of 81 card objects with unique attribute-expression combinations.
     * @return the array list
     */
    public void setCards() {
        ArrayList<ICard> allCards = new ArrayList<>();

        for (int i = 0; i < ICard.Colour.values().length; i++) {
            for (int j = 0; j < ICard.Shape.values().length; j++) {
                for (int k = 0; k < ICard.Number.values().length; k++) {
                    for (int l = 0; l < ICard.LineType.values().length; l++) {

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

        this.cards = (ICard[]) allCards.toArray();
    }

    public ICard[] getCards() { return this.cards; }

}
