package stacs.starcade.frontend;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.ClientModel;
import stacs.starcade.frontend.view.sub.info.sub.CurrentSetPane;

public class CurrentSetPaneTests {

    @Test
    void mustInstantiateCurrentSetPane() {
        CurrentSetPane currentSetPane = new CurrentSetPane(mock(ClientModel.class), mock(Controller.class));
        assertNotNull(currentSetPane);
    }

}
