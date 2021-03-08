package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel for Set cards chosen by the player.
 */
public class CurrentSetPane extends JPanel implements Observer {

    private FrontendModel model;
    private Controller controller;

    public CurrentSetPane(FrontendModel model, Controller controller) {
        this.model = model;
        this.controller = controller;
        ((Observable) model).addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
