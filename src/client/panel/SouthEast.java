/**
 * 
 */
package client.panel;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import client.Session;
import client.component.ImageNavigationComponent;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class SouthEast extends JPanel {
	private FieldHelpPanel fHelp;
	private ImageNavigationComponent imgNav;
	private Session session;
	
	/**
	 * @param session
	 */
	public SouthEast(Session session) {
		super();
		
		this.session = session;
		//this.setPreferredSize(new Dimension(400,300));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		fHelp = new FieldHelpPanel(session);
		imgNav = new ImageNavigationComponent(session);
		
		JScrollPane sp1 = new JScrollPane(fHelp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS ); 
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Field Help", sp1);
		tabs.addTab("Image Navigation", imgNav);
		
		this.add(tabs);
	}

}
