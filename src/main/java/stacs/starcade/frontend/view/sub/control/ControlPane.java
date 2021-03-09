package stacs.starcade.frontend.view.sub.control;

import stacs.starcade.backend.impl.LeaderBoard;
import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.ClientModel;
import stacs.starcade.frontend.model.IClientModel;
import stacs.starcade.frontend.view.sub.info.sub.LeaderBoardPane;
import stacs.starcade.shared.ICard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel for Buttons controller.
 */
// TODO: start game, end game, start nextround, validate set. -> 4 buttons should be implemented
public class ControlPane extends JPanel implements Observer {
    private IClientModel model;
    private IController controller;

    private JButton startGameButton;
    private JButton checkSetButton;
    private JButton nextRoundButton;
    private JButton endGameButton;

    /**
     * Button text of START GAME.
     */
    public static final String START_GAME = "START GAME";
    /**
     * Button text of CHECK SET.
     */
    public static final String CHECK_SET = "CHECK SET";
    /**
     * Button text of NEXT ROUND.
     */
    public static final String NEXT_ROUND = "NEXT ROUND";
    /**
     * Button text of END GAME.
     */
    public static final String END_GAME = "END GAME";

    public ControlPane(IClientModel model, IController controller) {
        this.model = model;
        this.controller = controller;

        ((Observable) this.model).addObserver(this);

        setBackground(new Color(255, 147, 0));

        generateButtons();
        generateButtonListener();
        addButtons();
    }

    /**
     * Generate buttons.
     */
    private void generateButtons() {
        startGameButton = new JButton(START_GAME);
        checkSetButton = new JButton(CHECK_SET);
        nextRoundButton = new JButton(NEXT_ROUND);
        endGameButton = new JButton(END_GAME);
    }

    /**
     * Generate buttons click listener.
     */
    private void generateButtonListener() {
        ActionListener al = new MyButtonListener();
        startGameButton.addActionListener(al);
        checkSetButton.addActionListener(al);
        nextRoundButton.addActionListener(al);
        endGameButton.addActionListener(al);
    }

    /**
     * Add button to the control panel.
     */
    private void addButtons() {
        add(startGameButton);
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(checkSetButton);
        add(nextRoundButton);
        add(endGameButton);
    }

    /**
     * If the {@link ClientModel} is updated, it will be invoked (observer notification)).
     *
     * @param arg0 the observable object.
     * @param arg1 an argument passed to the {@code notifyObservers} method.
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        repaint();
    }

    /**
     * Mouse click event listener,
     */
    class MyButtonListener implements ActionListener {
        /**
         * When an event occurs, it will be executed.
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Object evt = actionEvent.getSource();

            if (evt == startGameButton) {
                controller.startGame();
            }

            if (evt == checkSetButton) {
                try {
                    controller.validateCards(model.getSelectedCards().toArray(new ICard[model.getSelectedCards().size()]));
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(null, "You had already selected this set!!",
                            "VALIDATION RESULT", JOptionPane.PLAIN_MESSAGE);
                }
            }

            if (evt == nextRoundButton) {
                if (model.getStatus() == IClientModel.GameStatus.RUNNING) {
                    int result = JOptionPane.showConfirmDialog(null, "Do you really want to start a new round?", "Question", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        controller.setUpCards();
                    }
                } else {
                    controller.setUpCards();
                }
            }

            if (evt == endGameButton) {
                if (model.getStatus() == IClientModel.GameStatus.RUNNING) {
                    int result = JOptionPane.showConfirmDialog(null, "Do you want to quit this game?", "Question", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        controller.disconnect();
                    }
                }
            }
        }
    }

}
