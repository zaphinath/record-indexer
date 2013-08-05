/**
 * 
 */
package client.panel;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import client.Session;
import client.component.FormEntryComponent;
import client.component.Table;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class SouthWest extends JPanel {
	Session session;
	private Table table;
	private FormEntryComponent fec;
	
	public SouthWest(Session s) {
		super();
		this.session = s;
		//this.setPreferredSize(new Dimension(400,300));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		table = new Table(session);
		fec = new FormEntryComponent(session);
		
		JPanel tPanel = new JPanel();
		//tPanel.setSize(new Dimension(470,250));
		tPanel.add(table);
		//table.setSize(new Dimension(450,250));
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.setPreferredSize(new Dimension(450,250));
		tabs.addTab("Table Entry", tPanel);
		tabs.addTab("Form Entry", fec);
		
		//this.add(Box.createRigidArea(new Dimension(0,5)));
		this.add(tabs);
	}
}
