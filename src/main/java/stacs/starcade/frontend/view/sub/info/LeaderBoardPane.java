package stacs.starcade.frontend.view.sub.info;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.IClientModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
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
    private DataModel dataModel;

    public LeaderBoardPane(IClientModel model, IController controller) {
        this.model = model;
        this.controller = controller;
        ((Observable) this.model).addObserver(this);

        setTable();

        setBackground(Color.PINK);
    }

    /**
     * Model for maintaining data of leaderboard.
     * Flexible as data can be edited with setData.
     */
    class DataModel extends AbstractTableModel {
        private String[][] data;
        private String[] headers;
        private int rows;
        private static final int cols = 3;

        public void setHeaders(String[] headers) {
            headers = new String[cols];
            this.headers = new String[]{headers[0], headers[1], headers[2]};
        }

        public void setData(String[][] data) {
            this.rows = data.length;
            this.data = new String[this.rows][data[0].length];
//            for (int i = 0; i < this.rows; i++) {
//                for (int j = 0; j < cols; j++) {
//
//                }
//            }
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return this.rows;
        }

        @Override
        public int getColumnCount() {
            return this.cols;
        }

        @Override
        public String getValueAt(int rowIndex, int columnIndex) {
            String val = this.data[rowIndex][columnIndex];
            return val;
        }

        @Override
        public String getColumnName(int col) {
            return this.headers[col];
        }
    }

    private void setTable() {
        dataModel = new DataModel();
        String[] headers = {"Player", "Rounds", "AvgTime"};
        String[][] data = {{model.getPlayerName(), null, null}};
        dataModel.setHeaders(headers);
        dataModel.setData(data);

        // Instantiate table and set auto size
        this.table = new JTable(dataModel);
        this.table.setShowGrid(true);
        this.table.setGridColor(Color.BLACK);
        this.table.setBorder(new LineBorder(Color.BLACK));
        this.table.setAutoCreateRowSorter(true);

        // Add table to scroll pane, add scroll pane to pane
        JScrollPane jSP = new JScrollPane(this.table);
        this.table.setFillsViewportHeight(true);
        jSP.setVerticalScrollBar(new JScrollBar());
        add(jSP);
    }

    @Override
    public void update(Observable o, Object arg) {
        resetTableData();
        System.out.println("Reset data!");
        repaint();
    }

    /**
     * Edits data s
     */
    private void resetTableData() {
        String[][] data = model.getLeaderBoard();
        dataModel.setData(data);
    }
}

