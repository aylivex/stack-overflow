import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.Box.createHorizontalStrut;
import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.event.RowSorterEvent.Type.SORTED;
import static javax.swing.event.RowSorterEvent.Type.SORT_ORDER_CHANGED;

/**
 * Demo app for <a href="https://stackoverflow.com/q/70968706"
 * >How to sort rows in one table in same order as another table</a>.
 * <p>This app shows two tables, the 2nd table order of rows follows
 * the sort order of the 1st table.
 * <p>The second version of the demo also syncs the sort order of the
 * 2nd table to the 1st table.
 */
public class TableSyncDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TableSyncDemo::new);
    }

    private TableSyncDemo() {
        final TableRowSorter<TableTwoModel> sorterOne = new TableRowSorter<>();
        final TableRowSorter<TableTwoModel> sorterTwo = new TableRowSorter<>();

        TableTwoModel tableOneData = new TableTwoModel(
                new String[] {"Row Id", "Person", "Color"},
                new Object[][] {
                    {1, "Tom",   "Black"},
                    {2, "Jane",  "White"},
                    {3, "Fred",  "Red"},
                    {4, "Steve", "Blue"},
                    {5, "Jim",   "Yellow"}
                },
                sorterTwo);
        TableTwoModel tableTwoData = new TableTwoModel(
                new String[] {"Row Id", "Fruit"},
                new Object[][]{
                        {1, "Orange"},
                        {2, "Apple"},
                        {3, "Pear"},
                        {4, "Lemon"},
                        {5, "Plum"}
                },
                sorterOne);

        sorterOne.setModel(tableOneData);
        sorterTwo.setModel(tableTwoData);

        JTable tableOne = new JTable(tableOneData);
        tableOne.setRowSorter(sorterOne);

        JTable tableTwo = new JTable(tableTwoData);
        tableTwo.setRowSorter(sorterTwo);

        sorterOne.addRowSorterListener(e -> resetSortKeys(e, sorterOne, sorterTwo));
        sorterTwo.addRowSorterListener(e -> resetSortKeys(e, sorterTwo, sorterOne));

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, X_AXIS));
        main.setBorder(createEmptyBorder(10, 10, 10, 10));
        main.add(new JScrollPane(tableOne));
        main.add(createHorizontalStrut(10));
        main.add(new JScrollPane(tableTwo));

        JFrame frame = new JFrame("Table Sync Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void resetSortKeys(RowSorterEvent e,
                               RowSorter<? extends TableModel> thisSorter,
                               RowSorter<? extends TableModel> otherSorter) {
        if (e.getType() == SORT_ORDER_CHANGED
                && thisSorter.getSortKeys().size() > 0) {
            otherSorter.setSortKeys(null);
        }
    }

    private static class TableOneModel extends AbstractTableModel {
        private final String[] columnNames;

        private final Object[][] data;

        public TableOneModel(String[] columnNames, Object[][] data) {
            this.columnNames = columnNames;
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }
    }

    private static class TableTwoModel extends TableOneModel
            implements RowSorterListener {

        private final RowSorter<? extends TableModel> otherSorter;

        public TableTwoModel(String[] columnNames, Object[][] data,
                             RowSorter<? extends TableModel> otherSorter) {
            super(columnNames, data);
            this.otherSorter = otherSorter;
            installListeners();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return super.getValueAt(
                    otherSorter.convertRowIndexToModel(rowIndex),
                    columnIndex);
        }

        private void installListeners() {
            otherSorter.addRowSorterListener(this);
        }

        @Override
        public void sorterChanged(RowSorterEvent e) {
            if (e.getType() == SORTED) {
                fireTableDataChanged();
            }
        }
    }
}
