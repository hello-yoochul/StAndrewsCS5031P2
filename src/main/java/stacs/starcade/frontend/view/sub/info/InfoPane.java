package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for {@link CurrentSetPane} and {@link LeaderBoardPane}.
 */
public class InfoPane extends JPanel {
    FrontendModel model;
    Controller controller;

    CurrentSetPane currentSetPane;
    LeaderBoardPane leaderBoardPane;

    public InfoPane(FrontendModel model, Controller controller) {
        this.model = model;
        this.controller = controller;

        setLayout(new GridLayout(2, 1));

        currentSetPane = new CurrentSetPane(this.model, this.controller);
        leaderBoardPane = new LeaderBoardPane(this.model, this.controller);

        add(currentSetPane);
        add(leaderBoardPane);
    }

    /**
     * When model has a change, it will be invoked to redraw
     */
    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
    }
}
