package stacs.starcade.frontend;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.ClientModel;
import stacs.starcade.frontend.view.sub.control.ControlPane;

public class ControlPaneTests {
    ControlPane controlPane;

    @BeforeEach
    public void setup() {
        controlPane = new ControlPane(mock(ClientModel.class), mock(Controller.class));
    }

    @Test
    void mustInstantiateControlPane() {
        assertNotNull(controlPane);
    }

}
