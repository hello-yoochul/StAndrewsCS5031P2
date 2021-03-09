package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.IClientModel;
import stacs.starcade.frontend.view.sub.info.sub.CurrentSetPane;
import stacs.starcade.frontend.view.sub.info.sub.LeaderBoardPane;
import stacs.starcade.frontend.view.sub.info.sub.PlayerInfoPane;
import stacs.starcade.frontend.view.sub.info.sub.TimerPane;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Info panel which consists of {@link CurrentSetPane}, {@link LeaderBoardPane} and {@link TimerPane}.
 */
public class InfoPane extends JPanel implements Observer {
    private IClientModel model;
    private IController controller;

    private TimerPane timerPane;
    private PlayerInfoPane playerInfoPane;
    private CurrentSetPane currentSetPane;
    private LeaderBoardPane leaderBoardPane;


    public InfoPane(IClientModel model, IController controller) {
        this.model = model;
        this.controller = controller;

        ((Observable) this.model).addObserver(this);

        setupInfoPane();
    }

    private void setupInfoPane() {
        setLayout(new BorderLayout(5,5));

        // Add single panes to info pane
        playerInfoPane = new PlayerInfoPane(this.model, this.controller);
        timerPane = new TimerPane(this.model, this.controller);
        currentSetPane = new CurrentSetPane(this.model, this.controller);
        leaderBoardPane = new LeaderBoardPane(this.model, this.controller);


        class GameInfoPane extends JPanel {
            public GameInfoPane(PlayerInfoPane pip, TimerPane tp) {
                add(pip);
                add(tp);
            }
        }

        add(new GameInfoPane(playerInfoPane, timerPane), BorderLayout.NORTH);
        add(currentSetPane, BorderLayout.CENTER);
        add(leaderBoardPane, BorderLayout.SOUTH);

        setBackground(new Color(146, 146, 146));
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
