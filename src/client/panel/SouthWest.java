/**
 * 
 */
package client.panel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
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
	private JFrame frame;
	
	public SouthWest(JFrame frame, Session s) {
		super();
		this.session = s;
		this.frame = frame;
		
		//this.setPreferredSize(new Dimension(400,300));
		//this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		table = new Table(frame, session);
		fec = new FormEntryPanel(frame, session);
		
		final JTabbedPane tabs = new JTabbedPane();
		tabs.setPreferredSize(new Dimension(450,230));
		tabs.addTab("Table Entry", table);
		tabs.addTab("Form Entry", fec);
		
		tabs.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Component comp = tabs.getSelectedComponent();
				if (comp.equals(fec)) {
					//TODO fix this
					if (session.getSelectedCell().getField() > 0) {
						fec.getValues().get(session.getSelectedCell().getField()-1).requestFocusInWindow();
						for (int i = 0; i < session.getNumRecords(); i++) {
							if (i == session.getSelectedCell().getRecord()) {
								for (int j = 1; j < session.getNumFields(); j++) {
									fec.getValues().get(j-1).setText(session.getValue(j, i));
					   		 	}
							}
						}
					}
				}
			}
		});
		//this.add(Box.createRigidArea(new Dimension(0,20)));
		this.add(tabs);
	}
	
	
}
