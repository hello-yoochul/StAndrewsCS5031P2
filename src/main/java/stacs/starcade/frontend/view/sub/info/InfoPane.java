package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.Controller;
import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.model.IFrontendModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Info panel which consists of {@link CurrentSetPane}, {@link LeaderBoardPane} and {@link TimerPane}.
 */
public class InfoPane extends JPanel implements Observer {
    private IFrontendModel model;
    private IController controller;

    TimerPane timerPane;
    private CurrentSetPane currentSetPane;
    private LeaderBoardPane leaderBoardPane;

    public InfoPane(IFrontendModel model, IController controller) {
        this.model = model;
        this.controller = controller;
        ((Observable) model).addObserver(this);

        setLayout(new GridLayout(3, 1));

        // Add single panes to info pane
        timerPane = new TimerPane(this.model, this.controller);

        //        new Thread(timerPane).start();

        currentSetPane = new CurrentSetPane(this.model, this.controller);
        leaderBoardPane = new LeaderBoardPane(this.model, this.controller);

        add(timerPane);
        add(currentSetPane);
        add(leaderBoardPane);
        this.setSize(200,800);
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
