package stacs.starcade.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.backend.impl.ServerModel;
import stacs.starcade.shared.ICard.Colour;
import stacs.starcade.shared.ICard.LineStyle;
import stacs.starcade.shared.ICard.Number;
import stacs.starcade.shared.ICard.Shape;

public class CardTests {

    private Card card;
    @BeforeEach
    public void setup() {
        card = new Card();
    }

    @Test
    void testCardsSetup() {
        assertTrue(card.equals(card));
    }

    @Test
    void testEqualsOtherObject() {
        assertFalse(card.equals(new ServerModel()));
    }

    @Test
    void testEqualsSameCard() {
        Card c = new Card();
        card.setColour(Colour.RED);
        c.setColour(Colour.RED);
        card.setLineStyle(LineStyle.DASHED);
        c.setLineStyle(LineStyle.DASHED);
        card.setNumber(Number.THREE);
        c.setNumber(Number.THREE);
        card.setShape(Shape.CIRCLE);
        c.setShape(Shape.CIRCLE);

        assertEquals(card, c);
    }

    @Test
    void testSetGetColour(){
        ICard.Colour c = Colour.RED;
        card.setColour(c);
        assertEquals(c, card.getColour());
    }

    @Test
    void testSetGetLineStyle(){
        ICard.LineStyle c = LineStyle.DASHED;
        card.setLineStyle(c);
        assertEquals(c, card.getLineStyle());
    }

    @Test
    void testSetGetNumber(){
        ICard.Number c = Number.THREE;
        card.setNumber(c);
        assertEquals(c, card.getNumber());
    }

    @Test
    void testSetGetShape(){
        ICard.Shape c = Shape.CIRCLE;
        card.setShape(c);
        assertEquals(c, card.getShape());
    }
}
