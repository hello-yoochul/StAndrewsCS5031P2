package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.model.FrontendModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel for {@link CurrentSetPane} and {@link LeaderBoardPane}.
 */
public class InfoPane extends JPanel implements Observer {
    private FrontendModel model;
    private Controller controller;

    TimerPane timerPane;
    private CurrentSetPane currentSetPane;
    private LeaderBoardPane leaderBoardPane;

    public InfoPane(FrontendModel model, Controller controller) {
        this.model = model;
        this.controller = controller;
        ((Observable) model).addObserver(this);

        setLayout(new GridLayout(3, 1));

        timerPane = new TimerPane(this.model, this.controller);
        currentSetPane = new CurrentSetPane(this.model, this.controller);
        leaderBoardPane = new LeaderBoardPane(this.model, this.controller);

        add(timerPane);
        add(currentSetPane);
        add(leaderBoardPane);
    }

    /**
     * When model has a change, it will be invoked to redraw
     */
    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
