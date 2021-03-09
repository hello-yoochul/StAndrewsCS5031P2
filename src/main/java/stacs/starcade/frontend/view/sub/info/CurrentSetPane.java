package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.IFrontendModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel for Set cards which are selected by the player.
 */
public class CurrentSetPane extends JPanel implements Observer {

    private IFrontendModel model;
    private IController controller;

    public CurrentSetPane(IFrontendModel model, IController controller) {
        this.model = model;
        this.controller = controller;

        ((Observable) this.model).addObserver(this);

        setBackground(Color.BLACK);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
