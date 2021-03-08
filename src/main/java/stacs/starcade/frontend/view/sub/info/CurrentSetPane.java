package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;

import javax.swing.*;

/**
 * Panel for Set cards chosen by the player.
 */
public class CurrentSetPane extends JPanel {

    private FrontendModel model;
    private Controller controller;

    public CurrentSetPane(FrontendModel model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }
}
