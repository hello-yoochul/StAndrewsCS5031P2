package stacs.starcade.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.backend.impl.Card;
import stacs.starcade.backend.impl.Controller;
import stacs.starcade.backend.impl.Model;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static stacs.starcade.backend.impl.Checks.isSet;
import static stacs.starcade.backend.model.ICard.Colour.*;
import static stacs.starcade.backend.model.ICard.LineStyle.*;
import static stacs.starcade.backend.model.ICard.Number.*;
import static stacs.starcade.backend.model.ICard.Shape.*;

public class ControllerTests {

    private IController c;
    private IModel mockModel;
    private IPlayer mockPlayer1;
    private IPlayer mockPlayer2;
    private ICard card1;
    private ICard card2;
    private ICard card3;
    private ICard[] threeCards;

    @BeforeEach
    public void setup() {
        c = new Controller(new Model());
        mockModel = mock(IModel.class);
        mockPlayer1 = mock(IPlayer.class);
        mockPlayer2 = mock(IPlayer.class);
        card1 = new Card();
        card2 = new Card();
        card3 = new Card();
        threeCards = new ICard[] {card1, card2, card3};
    }

    @Test
    void testRecogniseThatGivenCardsMakeASet() throws IllegalArgumentException {
        // Define card properties
        // Colours and shapes are all the same
        // Numbers and LineStyles are all different
        card1.setColour(BLUE);
        card1.setShape(TRIANGLE);
        card1.setNumber(NULL);
        card1.setLineStyle(DASHED);

        card2.setColour(GREEN);
        card2.setShape(CIRCLE);
        card2.setNumber(NULL);
        card2.setLineStyle(DASHED);

        card3.setColour(RED);
        card3.setShape(TRIANGLE);
        card3.setNumber(NULL);
        card3.setLineStyle(DASHED);

        assertTrue(isSet(threeCards));
    }

    @Test
    void testRecogniseThatGivenCardsDoNotMakeASet() throws IllegalArgumentException {
        // Define card properties
        // No property satisfies the conditions for the three cards making up a set
        card1.setColour(BLUE);
        card1.setShape(TRIANGLE);
        card1.setNumber(NULL);
        card1.setLineStyle(DASHED);

        card2.setColour(GREEN);
        card2.setShape(CIRCLE);
        card2.setNumber(NULL);
        card2.setLineStyle(DASHED);

        card3.setColour(RED);
        card3.setShape(TRIANGLE);
        card3.setNumber(ONE);
        card3.setLineStyle(DOTTED);

        assertFalse(isSet(threeCards));
    }

    @Test
    void testIfValidSetHasBeenSuccessfullyStoredWithOwner() {
        // Define card properties and assign same owner for each player
        // Colours and shapes are all the same
        // Numbers and LineStyles are all different
        card1.setColour(BLUE);
        card1.setShape(TRIANGLE);
        card1.setNumber(NULL);
        card1.setLineStyle(DASHED);
        card1.setOwner(mockPlayer1);

        card2.setColour(GREEN);
        card2.setShape(CIRCLE);
        card2.setNumber(NULL);
        card2.setLineStyle(DASHED);
        card2.setOwner(mockPlayer1);

        card3.setColour(RED);
        card3.setShape(TRIANGLE);
        card3.setNumber(NULL);
        card3.setLineStyle(DASHED);
        card3.setOwner(mockPlayer1);

        // Triggers logging set for set owner mockPlayer1
        this.c.validateCards(threeCards);

        assertTrue(threeCards.equals(mockPlayer1.getSetsLog().get(0)));
    }

    @Test
    void testSuccessfulDenialWhenTryingToValidateJustTwoCards() {
        ICard twoCards[] = {card1, card2};
        String expectedMessage = "A set can only consist of exactly three cards!";

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
            // Logging an invalid score (7) for Twos should cause an exception
            this.c.validateCards(twoCards);
        });
        String actualMessage = iae.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testSuccessfulDenialWhenTryingToValidateCardsFromDifferentOwners() {
        String expectedMessage = "Given cards do not belong to the same player.";

        card1.setOwner(mockPlayer1);
        card2.setOwner(mockPlayer1);
        card3.setOwner(mockPlayer2);

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
            this.c.validateCards(threeCards);
        });
        String actualMessage = iae.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}
