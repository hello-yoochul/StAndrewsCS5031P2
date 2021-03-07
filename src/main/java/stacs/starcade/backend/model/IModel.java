package stacs.starcade.backend.model;

import java.util.ArrayList;

public interface IModel {
    /**
     * Generates an array list of 81 card objects with unique attribute-expression combinations.
     */
    void setCards();

    ArrayList<ICard> getCards();

    void logSet(ICard[] threeCards);
}
