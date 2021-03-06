package stacs.starcade.frontend.view;

import stacs.starcade.frontend.controller.FrontendController;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.view.sub.CardPanel;
import stacs.starcade.frontend.view.sub.ControlPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * This MainView create swing window with two panels: top panel and
 * canvas panel. Top panel includes buttons and text fields, and canvas
 * panel is for drawing and clicking event.
 *
 * @author 200012756
 */
public class FrontendView extends JFrame implements Observer {
    private final FrontendController controller;
    private final FrontendModel model;

    ControlPanel controlPanel;
    CardPanel cardPanel;

    private static final int DEFAULT_FRAME_WIDTH = 1600;
    private static final int DEFAULT_FRAME_HEIGHT = 900;

    public FrontendView(FrontendModel model, FrontendController controller) {
        super("Set Game");
        this.model = model;
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        // when model changed, panels become updated.
        ((Observable) model).addObserver(this);
        setUpComponents();
        setVisible(true);
    }

    private void setUpComponents() {
        controlPanel = new ControlPanel(controller);
        cardPanel = new CardPanel(controller, model);

        add(controlPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    /**
     * If the model updates, it will be invoked (observer notification)).
     *
     * @param arg0 the observable object.
     * @param arg1 an argument passed to the {@code notifyObservers} method.
     */
    public void update(Observable arg0, Object arg1) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        repaint();
                    }
                });
    }
}



