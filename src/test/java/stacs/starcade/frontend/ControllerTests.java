package stacs.starcade.frontend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.model.IFrontendModel;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.ICard;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Tests for the {@link Controller} class.
 */
public class ControllerTests {
    Controller controller;
    FrontendModel model;

    @BeforeEach
    void setup() {
        model = new FrontendModel();
        controller = new Controller(model);
    }

    @Test
    void mustBeRunning() {
        controller.startGame();
        assertEquals(IFrontendModel.GameStatus.RUNNING, model.getStatus());
    }

    @Test
    void mustPause() {
        controller.pauseGame();
        assertEquals(IFrontendModel.GameStatus.PAUSED, model.getStatus());
    }

    @Test
    void mustResume() {
        controller.startGame();
        assertEquals(IFrontendModel.GameStatus.RUNNING, model.getStatus());

        controller.pauseGame();
        assertEquals(IFrontendModel.GameStatus.PAUSED, model.getStatus());

        controller.resumeGame();
        assertEquals(IFrontendModel.GameStatus.RUNNING, model.getStatus());
    }

    @Test
    void mustSetPlayerId() {
        controller.startGame();
        controller.setPlayerId();
        assertNotNull(model.getPlayerId());
    }

    @Test
    void mustSetUpBoard() {
        controller.startGame();
        controller.setUpCards();
        assertTrue(model.getCards().size() != 0);
    }

    @Test
    void mustSelectCard() {
        ICard mockCard = mock(Card.class);
        controller.selectCard(mockCard);
        assertTrue(model.getCards().size() != 0);
    }

    // TODO: We are already checking this in ControllerTests2

//    @Test
//    void mustCheckIfItIsSet() {
//        controller.startGame();
//        // when it is not set
//        // TODO: choose three cards which are not set
//        assertFalse(controller.isSet());
//
//        // when it is set
//        // TODO: choose three cards which are set
//        assertTrue(controller.isSet());
//    }
}
