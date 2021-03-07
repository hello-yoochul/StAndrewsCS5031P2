package stacs.starcade.backend.impl;

import stacs.starcade.shared.Card;
import stacs.starcade.shared.ICard;

import java.util.ArrayList;

public class Model implements IModel {

    ArrayList<ICard> allCards;

    public Model() {
        setCards();
    }

    /**
     * Generates an array list of 81 card objects with unique attribute-expression combinations.
     */
    public void setCards() {
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

    public ArrayList<ICard> getCards() {
        return allCards;
    }


}
