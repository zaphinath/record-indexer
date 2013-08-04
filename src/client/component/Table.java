/**
 * 
 */
package client.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import client.Session;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class Table extends JComponent {
	private Session session;
	private JTable table;
	private TableModel tm;
	
	public Table(Session s) {
		super();
		this.session = s;
		//this.setPreferredSize(new Dimension(450,250));
		tm = new TableModel(session);
		table = new JTable(tm);
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		for (int i = 0; i < tm.getColumnCount(); ++i) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(150);
		}
		
		//System.out.println(table.getColumnCount());
		
		JPanel rootPanel = new JPanel(new BorderLayout());
		rootPanel.add(table.getTableHeader(), BorderLayout.NORTH);
		rootPanel.add(table, BorderLayout.CENTER);

		this.add(rootPanel);
	}
	
}
/*
@SuppressWarnings("serial")
class ColorCellRenderer extends JLabel implements TableCellRenderer {

	private Border unselectedBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
	private Border selectedBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

	public ColorCellRenderer() {
		
		setOpaque(true);
		setFont(getFont().deriveFont(16.0f));
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {

		//Color c = ColorUtils.fromString((String)value);
		//this.setBackground(c);
		
		if (isSelected) {
			this.setBorder(selectedBorder);
		}
		else {
			this.setBorder(unselectedBorder);
		}
		
		this.setText((String)value);
		
		return this;
	}

}
*/