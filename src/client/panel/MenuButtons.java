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
import client.SessionListener;
import client.model.Cell;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class MenuButtons extends JPanel implements SessionListener {
	
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
		
		this.add(zoomIn);
		this.add(zoomOut);
		this.add(invertImage);
		this.add(toggleHighlights);
		this.add(save);
		this.add(submit);
		
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
				session.setZoomLevel(session.getZoomLevel() + 1);
			} else if (e.getSource() == zoomOut) {
				session.setZoomLevel(session.getZoomLevel() - 1);
			} else if (e.getSource() == invertImage) {
				
			} else if (e.getSource() == toggleHighlights) {
				
			} else if (e.getSource() == save) {
				
			} else if (e.getSource() == submit) {
				
			}
		}
	};

	
	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#valueChanged(client.model.Cell, java.lang.String)
	 */
	@Override
	public void valueChanged(Cell cell, String newValue) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#selectedCellChanged(client.model.Cell)
	 */
	@Override
	public void selectedCellChanged(Cell newSelectedCell) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#zoomeLevelChanged(int)
	 */
	@Override
	public void zoomeLevelChanged(int zoom) {
		// TODO Auto-generated method stub
		
	}

}
