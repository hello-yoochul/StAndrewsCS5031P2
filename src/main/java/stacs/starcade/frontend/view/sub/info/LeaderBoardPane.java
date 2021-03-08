package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;

import javax.swing.*;

/**
 * Panel for the leader board.
 */
public class LeaderBoardPane extends JPanel {
    private FrontendModel model;
    private Controller controller;

    public LeaderBoardPane(FrontendModel model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }
}

