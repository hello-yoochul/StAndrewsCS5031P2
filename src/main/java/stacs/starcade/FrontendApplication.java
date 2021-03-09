package stacs.starcade;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.ClientModel;
import stacs.starcade.frontend.view.main.FrontendView;

/**
 * Entry method for frontend
 */
public class FrontendApplication {
    public static void main(String[] args) {
        ClientModel setGameModel = new ClientModel();
        Controller controller = new Controller(setGameModel);
        new FrontendView(setGameModel, controller);
    }
}
