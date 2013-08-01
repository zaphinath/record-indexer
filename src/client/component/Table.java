/**
 * 
 */
package client.component;

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
	
	Table(Session s) {
		this.session = s;
		tm = new TableModel(session);
		table = new JTable(tm);
	}
}
