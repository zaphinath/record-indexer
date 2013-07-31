/**
 * 
 */
package client.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		zoomIn.addActionListener(actionListener);
		zoomOut.addActionListener(actionListener);
		invertImage.addActionListener(actionListener);
		toggleHighlights.addActionListener(actionListener);
		save.addActionListener(actionListener);
		submit.addActionListener(actionListener);
		
	}
	
	private ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == zoomIn) {
				
			} else if (e.getSource() == zoomOut) {
				
			} else if (e.getSource() == invertImage) {
				
			} else if (e.getSource() == toggleHighlights) {
				
			} else if (e.getSource() == save) {
				
			} else if (e.getSource() == submit) {
				
			}
		}
	};

}
