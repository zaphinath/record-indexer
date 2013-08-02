/**
 * 
 */
package client.component;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTable;

import client.Session;
import client.SessionListener;

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
}
