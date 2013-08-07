/**
 * 
 */
package client.panel;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import client.Session;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class SouthWest extends JPanel {
	Session session;
	private Table table;
	private FormEntryPanel fec;
	
	public SouthWest(Session s) {
		super();
		this.session = s;
		//this.setPreferredSize(new Dimension(400,300));
		//this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		table = new Table(session);
		fec = new FormEntryPanel(session);
		
		final JTabbedPane tabs = new JTabbedPane();
		tabs.setPreferredSize(new Dimension(450,230));
		tabs.addTab("Table Entry", table);
		tabs.addTab("Form Entry", fec);
		
		tabs.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Component comp = tabs.getSelectedComponent();
				if (comp.equals(fec)) {
					//TODO fix this
					fec.getValues().get(session.getSelectedCell().getField()-1).requestFocusInWindow();
				}
			}
		});
		//this.add(Box.createRigidArea(new Dimension(0,20)));
		this.add(tabs);
	}
	
	
}
