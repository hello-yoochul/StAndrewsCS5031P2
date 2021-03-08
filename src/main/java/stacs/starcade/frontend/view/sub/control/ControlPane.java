package stacs.starcade.frontend.view.sub.control;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel for Buttons controller.
 */
// TODO: start game, end game, start nextround, validate set. -> 4 buttons should be implemented
public class ControlPane extends JPanel {
    private FrontendModel model;
    private Controller controller;

    private JButton startGameButton;
    private JButton checkSetButton;
    private JButton endGameButton;

    public static final String START = "START";
    public static final String CHECK_SET = "CHECK SET";
    public static final String END_GAME = "END";

    public ControlPane(FrontendModel model,Controller controller) {
        this.model = model;
        this.controller = controller;

        setBackground(Color.gray);

        generateButtons();
        generateButtonListener();
        addButtons();
    }

    private void generateButtons() {
        startGameButton = new JButton(START);
        checkSetButton = new JButton(CHECK_SET);
        endGameButton = new JButton(END_GAME);
    }

    private void generateButtonListener() {
        ActionListener al = new MyButtonListener();
        startGameButton.addActionListener(al);
        checkSetButton.addActionListener(al);
        endGameButton.addActionListener(al);
    }

    private void addButtons() {
        add(startGameButton);
        add(checkSetButton);
        add(endGameButton);
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
//                controller.validateCards();
            }

            if (evt == endGameButton) {
//                controller.validateCards();
            }
        }
    }

}
