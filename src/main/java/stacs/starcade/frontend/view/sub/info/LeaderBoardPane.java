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
 * Panel for the leader board.
 */
public class LeaderBoardPane extends JPanel implements Observer {
    private IFrontendModel model;
    private IController controller;
    private JTable table;

    public LeaderBoardPane(IFrontendModel model, IController controller) {
        this.model = model;
        this.controller = controller;
        ((Observable) this.model).addObserver(this);

//        this.setLayout(new BorderLayout());

        setTable();

        setBackground(Color.PINK);
    }

    private void setTable() {
        controller.getLeaderBoard();

        String[] colNames = {"Player", "Rounds", "AvgTime"};
        String[][] data = model.getLeaderBoard();

        this.table = new JTable(data, colNames);
        this.table.setShowGrid(true);
        this.table.setGridColor(Color.BLACK);
        this.table.setVisible(true);

        // Add table to pane
        JScrollPane jSP = new JScrollPane(this.table);
        this.table.setFillsViewportHeight(true);
        jSP.setVerticalScrollBar(new JScrollBar());
        add(jSP);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

