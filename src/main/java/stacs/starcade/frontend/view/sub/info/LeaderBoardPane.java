package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel for the leader board.
 */
public class LeaderBoardPane extends JPanel implements Observer {
    private FrontendModel model;
    private Controller controller;

    public LeaderBoardPane(FrontendModel model, Controller controller) {
        this.model = model;
        this.controller = controller;
        ((Observable) model).addObserver(this);

        setBackground(Color.PINK);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

