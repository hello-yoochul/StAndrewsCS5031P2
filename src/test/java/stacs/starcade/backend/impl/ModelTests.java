package stacs.starcade.backend.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.shared.ICard;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTests {

    private Model model;
    @BeforeEach
    public void setup() {
        model = new Model();
    }

    @Test
    void testCardsSetup() {
        assertEquals(81,model.getAllCards().size());
    }

    @Test
    void checkTwelveCards() {
        List<ICard> cards = model.getTwelveCards();
        assertEquals(12, cards.size());
    }

}
