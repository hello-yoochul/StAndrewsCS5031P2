package stacs.starcade.backend.impl;

import stacs.starcade.backend.model.ICard;
import stacs.starcade.backend.model.IModel;
import stacs.starcade.backend.model.IPlayer;

import static stacs.starcade.backend.impl.Checks.isSameOwner;
import static stacs.starcade.backend.impl.Checks.isSet;

public class Controller implements stacs.starcade.backend.model.IController {
    private IModel model;

    public Controller(IModel model) {
        this.model = model;
    }

    public void validateCards(ICard[] threeCards) throws IllegalArgumentException {
        // Validate input
        if (threeCards.length != 3) {
            throw new IllegalArgumentException("A set can only consist of exactly three cards!");
        } else if(!isSameOwner(threeCards)) {
            throw new IllegalArgumentException("Given cards do not belong to the same player.");
        }

        if (isSet(threeCards)) {
            // Check whether owner (player) of card objects has already logged this set of cards
            // Trigger model to log set for owner of cards
            model.logSet(threeCards);
        } else {
            // Trigger Error Message that three given cards do not make a set
        }
    }

}
