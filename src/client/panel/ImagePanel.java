/**
 * 
 */
package client.panel;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.Session;
import client.SessionListener;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements SessionListener {
	private Session session;
	private Image image;
	private JPanel imgPanel;
	private BufferedImage bufImage;
	
	/**
	 * @param session
	 */
	public ImagePanel(Session session) {
		super();
		this.session = session;
		if (session.isHaveBatch()) {
			initialize();
		} else if (!session.isHaveBatch()) {
			destroy();
		}
	}

	private void initialize() {
		this.setOpaque(true);
		this.setBackground(Color.DARK_GRAY);
		try {
			image = ImageIO.read(session.getCurrentBatch().getImageUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}
		imgPanel = new JPanel();
		//imgPanel.setSize(image.getWidth(observer), height)
		JLabel img = new JLabel(new ImageIcon(image));
		imgPanel.add(img);
		this.add(imgPanel);
		
	}
	
	@SuppressWarnings("deprecation")
	private void destroy() {
		this.setOpaque(true);
		this.setBackground(Color.DARK_GRAY);
		image = null;
		imgPanel.disable();
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		if (session.isHaveBatch()) {
			initialize();
		} else if (!session.isHaveBatch()) {
			destroy();
		}	
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

	/* (non-Javadoc)
	 * @see client.SessionListener#toggleHighlightsChanged(boolean)
	 */
	@Override
	public void toggleHighlightsChanged(boolean toggle) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#imageInversionChanged(boolean)
	 */
	@Override
	public void imageInversionChanged(boolean inversion) {
		assert bufImage != null;
		for (int i=0; i < bufImage.getWidth(); i++) {
			for (int j = 0; j < bufImage.getHeight(); j++) {
				bufImage.setRGB(i,j,bufImage.getRGB(i, j) ^ 0xFF000000);
			}
		}
		//redraw image
	}
}
