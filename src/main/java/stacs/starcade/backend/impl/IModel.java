package stacs.starcade.backend.impl;

import stacs.starcade.shared.ICard;

import java.util.ArrayList;

public interface IModel {

    ArrayList<ICard> getAllCards();

    /**
     * Gets twelve cards with 5 sets.
     * Persists the cards, if no set of cards exists, create new set
     * @return ArrayList of twelve cards
     */
    ArrayList<ICard> getTwelveCards();
}
