package stacs.starcade.frontend.view.main;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.ClientModel;
import stacs.starcade.frontend.model.IClientModel;
import stacs.starcade.frontend.view.sub.card.CardPane;
import stacs.starcade.frontend.view.sub.control.ControlPane;
import stacs.starcade.frontend.view.sub.info.InfoPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * This is Main view of frontend.
 * It creates 3 sub parts view: controlPane (button for request to server), cardPane (12 cards view),
 * and infoPane(current sets and leaderBoard).
 */
public class FrontendView extends JFrame implements Observer {
    /**
     * Default frame width for the main view.
     */
    private static final int DEFAULT_FRAME_WIDTH = 1600;
    /**
     * Default frame height for the main view.
     */
    private static final int DEFAULT_FRAME_HEIGHT = 900;

    private IController controller;
    private IClientModel model;

    private ControlPane controlPanel;
    private CardPane cardPanel;
    private InfoPane infoPanel;


    public FrontendView(IClientModel model, IController controller) {
        super("Set Game");
        this.model = model;
        this.controller = controller;

        // when model changed, panels are updated.
        ((Observable) this.model).addObserver(this);
        String name = JOptionPane.showInputDialog(this, "Enter your name:");
        model.setPlayerName(name.replace(" ","_"));

        controller.register();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        addExitListener();

        setUpComponents();
        setVisible(true);

        controller.pollForLeaderBoard();
    }

    /**
     * Defines actions when window is closed.
     * Client is disconnected from sever.
     */
    private void addExitListener() {
        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                controller.disconnect();
                super.windowClosing(e);
            }
        });
    }

    /**
     * Creates 3 sub parts view: controlPane (button for request to server), cardPane (12 cards view),
     * and infoPane(current sets and leaderBoard).
     */
    private void setUpComponents() {
        setLayout(new BorderLayout(10, 10));

        add((controlPanel = new ControlPane(this.model, this.controller)), BorderLayout.NORTH);
        add((cardPanel = new CardPane(this.model, this.controller)), BorderLayout.CENTER);
        cardPanel.setPreferredSize(new Dimension(1000, 850));
        add((infoPanel = new InfoPane(this.model, this.controller)), BorderLayout.EAST);
        infoPanel.setSize(new Dimension(600, 850));
    }

    /**
     * If the {@link ClientModel} is updated, it will be invoked (observer notification)).
     *
     * @param arg0 the observable object.
     * @param arg1 an argument passed to the {@code notifyObservers} method.
     */
    public void update(Observable arg0, Object arg1) {
        repaint();
    }
}



