/**
 * 
 */
package client.component;

import javax.swing.JComponent;

import client.Session;
import client.SessionListener;

/**
 * @author zaphinath
 *
 */
public class FormEntryComponent extends JComponent implements SessionListener {
	private Session session;
	
	public FormEntryComponent(Session s) {
		this.session = s;
	}
}
