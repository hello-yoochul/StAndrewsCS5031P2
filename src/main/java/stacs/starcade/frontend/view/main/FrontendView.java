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
    private final IController controller;
    private final IClientModel model;

    private ControlPane controlPanel;
    private CardPane cardPanel;
    private InfoPane infoPanel;

    private static final int DEFAULT_FRAME_WIDTH = 1600;
    private static final int DEFAULT_FRAME_HEIGHT = 900;

    public FrontendView(IClientModel model, IController controller) {
        super("Set Game");
        this.model = model;
        this.controller = controller;

        // when model changed, panels are updated.
        ((Observable) this.model).addObserver(this);

        model.setPlayerName(JOptionPane.showInputDialog(this, "Enter your name:"));

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
        // obtained from https://stackoverflow.com/questions/33576358/how-to-use-java-swing-layout-manager-to-make-this-gui
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        // Add control pane to grid
        add((controlPanel = new ControlPane(this.model, this.controller)), gbc);

        // Add card panel to grid
        gbc.weighty = 1;
        gbc.gridy++;
        add((cardPanel = new CardPane(this.model, this.controller)), gbc);

        // Add info pane to
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.weightx = 0.55;
        add((infoPanel = new InfoPane(this.model, this.controller)), gbc);
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



