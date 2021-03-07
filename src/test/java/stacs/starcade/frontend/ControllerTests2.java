package stacs.starcade.frontend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.backend.impl.IModel;
import stacs.starcade.backend.impl.IPlayer;
import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.shared.Card;
import stacs.starcade.backend.impl.Player;
import stacs.starcade.shared.ICard;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static stacs.starcade.shared.Checks.isSet;
import static stacs.starcade.shared.ICard.Colour.*;
import static stacs.starcade.shared.ICard.LineStyle.*;
import static stacs.starcade.shared.ICard.Number.*;
import static stacs.starcade.shared.ICard.Shape.*;

public class ControllerTests2 {

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
        c = new Controller(new FrontendModel());
        mockModel = mock(IModel.class);
        mockPlayer1 = new Player();
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
        card3.setShape(SQUARE);
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
        card3.setShape(SQUARE);
        card3.setNumber(ONE);
        card3.setLineStyle(DOTTED);

        assertFalse(isSet(threeCards));
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

}
