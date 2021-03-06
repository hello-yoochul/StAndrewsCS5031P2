package stacs.starcade.backend.model;

import java.util.ArrayList;

public interface IModel {
    /**
     * Generates an array list of 81 card objects with unique attribute-expression combinations.
     * @return the array list
     */
    void setCards();

    /**
     * @return an array that accounts for all 81 individual card objects possibly used in SetGame.
     */
    ICard[] getCards();

    void logSet(ICard[] threeCards);
}
