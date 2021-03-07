package stacs.starcade.frontend.view.main;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.view.sub.card.CardPane;
import stacs.starcade.frontend.view.sub.control.ControlPane;
import stacs.starcade.frontend.view.sub.info.InfoPane;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 *
 */
public class FrontendView extends JFrame implements Observer {
    private final Controller controller;
    private final FrontendModel model;

    ControlPane controlPanel;
    CardPane cardPanel;
    InfoPane infoPanel;

    private static final int DEFAULT_FRAME_WIDTH = 1600;
    private static final int DEFAULT_FRAME_HEIGHT = 900;

    public FrontendView(FrontendModel model, Controller controller) {
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

//        pack();
//        setMinimumSize(getPreferredSize());
    }

    private void setUpComponents() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        add((controlPanel = new ControlPane(this.model,this.controller)), gbc);
        gbc.weighty = 1;
        gbc.gridy++;
        add((cardPanel = new CardPane(this.model, this.controller)), gbc);

        gbc.gridy = 0;
        gbc.gridx++;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1;
        gbc.weightx = 0.5;
        add((infoPanel = new InfoPane(this.model, this.controller)), gbc);
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



