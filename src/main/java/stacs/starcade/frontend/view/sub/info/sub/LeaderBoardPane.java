package stacs.starcade.frontend.view.sub.info.sub;

import stacs.starcade.frontend.controller.IController;
import stacs.starcade.frontend.model.IClientModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel for the leader board.
 */
public class LeaderBoardPane extends JPanel implements Observer {
    private static final int AVG_TIME_COL = 2;
    private static final int DEFAULT_FRAME_WIDTH = 800;
    private static final int DEFAULT_FRAME_HEIGHT = 500;
    private IClientModel model;
    private IController controller;
    private JPanel panel;
    private JTable table;
    private TableRowSorter rowSorter;
    private DataModel dataModel;

    public LeaderBoardPane(IClientModel model, IController controller) {
        this.model = model;
        this.controller = controller;
        ((Observable) this.model).addObserver(this);

        setPanel();
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
            this.headers = new String[cols];
            this.headers = new String[]{headers[0], headers[1], headers[2]};
        }

        public void setData(String[][] data) {
            this.rows = data.length;
            this.data = new String[this.rows][data[0].length];
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

    /**
     * Initialises table and its properties.
     */
    private void setPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(20,5));

        dataModel = new DataModel();
        String[] headers = {"Player", "Rounds", "AvgTime"};
        String[][] data = {{model.getPlayerName(), null, null}};
        dataModel.setHeaders(headers);
        dataModel.setData(data);

        // Instantiate table and set auto size
        this.table = new JTable(dataModel);
        this.table.setShowGrid(false);
        this.table.setShowHorizontalLines(true);
        this.table.setShowVerticalLines(false);
        this.table.setGridColor(Color.BLACK);

        // Create sorter for table
        rowSorter = new TableRowSorter(this.dataModel);
        ArrayList<RowSorter.SortKey> keys = new ArrayList<>();
        keys.add(new RowSorter.SortKey(AVG_TIME_COL, SortOrder.ASCENDING));
        rowSorter.setSortKeys(keys);

        // Add table to scroll pane, add scroll pane to pane
        JScrollPane jSP = new JScrollPane(this.table);
        this.table.setFillsViewportHeight(true);
        jSP.setVerticalScrollBar(new JScrollBar());


        JLabel lbLabel = new JLabel("Global Leaderboard: ");
        add(lbLabel);
        add(jSP);
    }

//    private JPanel button() {
//        JPanel buttonPanel = new JPanel();
//        closeButton = new JButton("Close Leaderboard");
//        closeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Object evt = e.getSource();
//                if (evt == closeButton) {
//
//                }
//            }
//        });
//        buttonPanel.add(closeButton);
//
//    }

//    /**
//     * Mouse click event listener,
//     */
//    class MyButtonListener implements ActionListener {
//        /**
//         * When an event occurs, it will be executed.
//         */
//        @Override
//        public void actionPerformed(ActionEvent actionEvent) {
//            Object evt = actionEvent.getSource();
//
//            if (evt == closeButton) {
//                ;
//            }
//        }
//    }

    /**
     * If the {@link IClientModel} is updated, it will be invoked (observer notification)).
     *
     * @param arg0 the observable object.
     * @param arg1 an argument passed to the {@code notifyObservers} method.
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        resetTableData();
        repaint();
    }

    /**
     * Updates data in leaderboard table when called.
     */
    private void resetTableData() {
        String[][] data = model.getLeaderBoard();
        dataModel.setData(data);
        rowSorter.sort();
    }
}

