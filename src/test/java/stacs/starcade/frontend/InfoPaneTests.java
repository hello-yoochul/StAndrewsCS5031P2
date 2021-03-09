package stacs.starcade.frontend;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.ClientModel;
import stacs.starcade.frontend.view.sub.info.InfoPane;
import stacs.starcade.frontend.view.sub.info.sub.LeaderBoardPane;

public class InfoPaneTests {

    @Test
    void mustInstantiateInfoPane() {
        InfoPane infoPane = new InfoPane(mock(ClientModel.class), mock(Controller.class));
        assertNotNull(infoPane);
    }

}
