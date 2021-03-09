package stacs.starcade.frontend.view.sub.info.sub;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.ClientModel;
import stacs.starcade.frontend.model.IClientModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel for the leader board.
 */
public class LeaderBoardPane extends JPanel implements Observer {
    private IClientModel model;
    private IController controller;
    private JTable table;

    public LeaderBoardPane(IClientModel model, IController controller) {
        this.model = model;
        this.controller = controller;
        ((Observable) this.model).addObserver(this);

        setTable();

        setBackground(Color.PINK);
    }

    private void setTable() {
        String[] colNames = {"Player", "Rounds", "AvgTime"};
        String[][] data = model.getLeaderBoard();

        // Instantiate table and set auto size
        this.table = new JTable(data, colNames);

        this.table.setShowVerticalLines(true);
        this.table.setShowHorizontalLines(true);
        this.table.setBorder(new LineBorder(Color.BLACK));
        this.table.setShowGrid(true);
        this.table.setGridColor(Color.BLACK);
        this.table.setVisible(true);

        // Add table to pane
        JScrollPane jSP = new JScrollPane(this.table);
        this.table.setFillsViewportHeight(true);
        jSP.setVerticalScrollBar(new JScrollBar());
        add(jSP);
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
}

