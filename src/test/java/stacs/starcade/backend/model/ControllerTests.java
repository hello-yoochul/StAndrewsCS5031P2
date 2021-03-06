package stacs.starcade.backend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.backend.impl.Card;
import stacs.starcade.backend.impl.Controller;
import stacs.starcade.backend.impl.Model;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static stacs.starcade.backend.impl.Checks.isSameOwner;
import static stacs.starcade.backend.impl.Checks.isSet;

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
        card1.setColour(0);
        card1.setShape(0);
        card1.setNumber(1);
        card1.setLineStyle(2);

        card2.setColour(1);
        card2.setShape(1);
        card2.setNumber(1);
        card2.setLineStyle(2);

        card3.setColour(2);
        card3.setShape(2);
        card3.setNumber(1);
        card3.setLineStyle(2);

        assertTrue(isSet(threeCards));
    }

    @Test
    void testRecogniseThatGivenCardsDoNotMakeASet() throws IllegalArgumentException {
        // Define card properties
        // No property satisfies the conditions for the three cards making up a set
        card1.setColour(0);
        card1.setShape(0);
        card1.setNumber(1);
        card1.setLineStyle(2);

        card2.setColour(1);
        card2.setShape(1);
        card2.setNumber(1);
        card2.setLineStyle(1);

        card3.setColour(1);
        card3.setShape(1);
        card3.setNumber(1);
        card3.setLineStyle(1);

        assertFalse(isSet(threeCards));
    }

    @Test
    void testIfValidSetHasBeenSuccessfullyStoredWithOwner() {
        // Define card properties and assign same owner for each player
        // Colours and shapes are all the same
        // Numbers and LineStyles are all different
        card1.setColour(0);
        card1.setShape(0);
        card1.setNumber(1);
        card1.setLineStyle(2);
        card1.setOwner(mockPlayer1);

        card2.setColour(1);
        card2.setShape(1);
        card2.setNumber(1);
        card2.setLineStyle(2);
        card2.setOwner(mockPlayer1);

        card3.setColour(2);
        card3.setShape(2);
        card3.setNumber(1);
        card3.setLineStyle(2);
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
