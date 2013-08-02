/**
 * 
 */
package client.component;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTable;

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
		this.setPreferredSize(new Dimension(450,250));
		tm = new TableModel(session);
		table = new JTable(tm);
		
		this.add(table);
	}


	
}
