package stacs.starcade.frontend.view.sub;

import stacs.starcade.frontend.controller.FrontendController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    FrontendController controller;

    private JButton startGameButton;
    private JButton checkSetButton;
    private JButton endGameButton;
    private JToggleButton pauseGameToggleButton;

    public static final String START = "START";
    public static final String CHECK_SET = "CHECK SET";
    public static final String END_GAME = "END";
    public static final String PAUSE = "PAUSE";
    public static final String UNPAUSE = "UNPAUSE";

    public ControlPanel(FrontendController controller) {
        this.controller = controller;
        generateButtons();
        generateButtonListener();
        addButtons();
    }

    private void generateButtons() {
        startGameButton = new JButton(START);
        checkSetButton = new JButton(CHECK_SET);
        endGameButton = new JButton(END_GAME);
        pauseGameToggleButton = new JToggleButton(PAUSE);
    }

    private void generateButtonListener() {
        ActionListener al = new MyButtonListener();
        startGameButton.addActionListener(al);
        checkSetButton.addActionListener(al);
        endGameButton.addActionListener(al);
        pauseGameToggleButton.addActionListener(al);
    }

    private void addButtons() {
        add(startGameButton);
        add(checkSetButton);
        add(pauseGameToggleButton);
    }
    
    class MyButtonListener implements ActionListener {
        /**
         * When an event occurs, it will be executed.
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Object evt = actionEvent.getSource();

            if (evt == startGameButton) {
                // send http://localhost:8080/game
                controller.startGame();
            }

            if (evt == checkSetButton) {
                controller.isSet();
            }

            if (evt == pauseGameToggleButton) {
                controller.pauseGame();
            }
        }
    }

}
