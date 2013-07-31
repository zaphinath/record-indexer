/**
 * 
 */
package client.panel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import client.Session;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class MenuButtons extends JPanel {
	
	private JButton zoomIn;
	private JButton zoomOut;
	private JButton invertImage;
	private JButton toggleHighlights;
	private JButton save;
	private JButton submit;
	
	private Session session;
	
	public MenuButtons(Session s) {
		super();
		session = s;
		
		zoomIn = new JButton("Zoom In");
		zoomOut = new JButton("Zoom Out");
		invertImage = new JButton("Invert Image");
		toggleHighlights = new JButton("Toggle Highlights");
		save = new JButton("Save");
		submit = new JButton("Submit");
		
		JToolBar menuBar = new JToolBar();
		menuBar.add(zoomIn);
		menuBar.add(zoomOut);
		menuBar.add(invertImage);
		menuBar.add(toggleHighlights);
		menuBar.add(save);
		menuBar.add(submit);
	}

}
