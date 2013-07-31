/**
 * 
 */
package client.panel;

import javax.swing.JPanel;

import client.Session;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class TableEntry extends JPanel {
	private Session session;
	
	public TableEntry(Session s) {
		this.session = s;
	}
}
