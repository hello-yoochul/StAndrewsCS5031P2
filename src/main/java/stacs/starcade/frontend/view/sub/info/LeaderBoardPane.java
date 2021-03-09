package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.IClientModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
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
        this.table = new JTable(data, colNames) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int width = component.getPreferredSize().width;
                TableColumn col = getColumnModel().getColumn(column);
                col.setPreferredWidth(Math.max(width + getIntercellSpacing().width, col.getPreferredWidth()));
                return component;
            }
        };
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

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

    @Override
    public void update(Observable o, Object arg) {
        setTable();
        repaint();
    }
}

