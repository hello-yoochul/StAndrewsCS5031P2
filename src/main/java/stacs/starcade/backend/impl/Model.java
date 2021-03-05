package stacs.starcade.backend.impl;

import stacs.starcade.backend.model.Card;
import stacs.starcade.backend.model.ICard;

import java.util.ArrayList;

public class Model {

    private ICard[] cards;

    public Model() {
        setCards();
    }

    /**
     * Generates an array list of 81 card objects with unique attribute-expression combinations.
     * @return the array list
     */
    private void setCards() {
        ArrayList<ICard> allCards = new ArrayList<>();
        int numExpressions = 3;

        for (int i = 0; i < numExpressions; i++) { // Colour
            for (int j = 0; j < numExpressions; j++) { // Shape
                for (int k = 0; k < numExpressions; k++) { // Number
                    for (int l = 0; l < numExpressions; l++) { // Line Style

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
