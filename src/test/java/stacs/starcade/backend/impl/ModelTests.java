package stacs.starcade.backend.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTests {

    private Model model;
    @BeforeEach
    public void setup() {
        model = new Model();
    }

    @Test
    void testCardsSetup() {
        assertEquals(81,model.getCards().size());
    }

}
