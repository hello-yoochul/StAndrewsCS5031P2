package stacs.starcade;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.view.FrontendView;

/**
 * Entry method for frontend
 */
public class FrontendApplication {
    public static void main(String[] args) {
        FrontendModel setGameModel = new FrontendModel();
        Controller controller = new Controller(setGameModel);
        new FrontendView(setGameModel, controller);
    }
}
