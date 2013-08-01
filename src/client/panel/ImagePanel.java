/**
 * 
 */
package client.panel;

import java.awt.Color;

import javax.swing.JPanel;

import client.Session;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	/**
	 * @param session
	 */
	public ImagePanel(Session session) {
		super();
		initialize();
	}

	private void initialize() {
		this.setOpaque(true);
		this.setBackground(Color.DARK_GRAY);
		
	}
}
