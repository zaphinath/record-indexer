/**
 * 
 */
package client.component;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTable;

import client.Session;
import client.SessionListener;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class Table extends JComponent implements SessionListener {
	private Session session;
	private JTable table;
	private TableModel tm;
	
	public Table(Session s) {
		super();
		
		this.session = s;
		this.setPreferredSize(new Dimension(400,250));
		tm = new TableModel(session);
		table = new JTable(tm);
		
		this.add(table);
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#valueChanged(client.model.Cell, java.lang.String)
	 */
	@Override
	public void valueChanged(Cell cell, String newValue) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#selectedCellChanged(client.model.Cell)
	 */
	@Override
	public void selectedCellChanged(Cell newSelectedCell) {
		// TODO Auto-generated method stub
		
	}
	
}
