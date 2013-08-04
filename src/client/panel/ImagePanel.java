/**
 * 
 */
package client.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.Session;
import client.SessionListener;
import client.component.DrawingImage;
import client.component.DrawingRect;
import client.component.DrawingShape;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements SessionListener {
	private Session session;
	private Image image;
	private JPanel rootPanel;
	private BufferedImage bufImage;
	private JLabel img;
	private ArrayList<DrawingShape> shapes;
	private Point2D lastPoint;
	private ArrayList<DrawingShape> dragShapes;
	
	/**
	 * @param session
	 */
	public ImagePanel(Session session) {
		super();
		this.session = session;
		this.setBackground(Color.DARK_GRAY);
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		this.addMouseWheelListener(mouseAdapter);
		
		shapes = new ArrayList<DrawingShape>();
		dragShapes = new ArrayList<DrawingShape>();
				
		rootPanel = new JPanel(new GridBagLayout());
		this.add(rootPanel);
		session.addListener(this);
		if (session.isHaveBatch()) {
			initialize();
		} else if (!session.isHaveBatch()) {
			destroy();
		}
	}

	private void initialize() {
		try {
			bufImage = ImageIO.read(session.getCurrentBatch().getImageUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}
		shapes.add(new DrawingImage(bufImage, new Rectangle2D.Double(0, 0, bufImage.getWidth(), bufImage.getHeight()), session, this));
		
		/*
		GridBagConstraints gbc = new GridBagConstraints();
		
		for (int i=0; i < session.getCurrentBatch().getNumRecords(); i++) {
			for (int j=0; j < session.getCurrentBatch().getNumFields(); j++) {
				double tmpHeight = session.getCurrentBatch().getRecordHeight();
				double tmpWidth = session.getCurrentBatch().getFields().get(j).getWidth();
				double tmpX = 0;
				double tmpY = 0;
				DrawingRect rect = new DrawingRect(new Rectangle2D.Double(tmpX, tmpY, tmpWidth, tmpHeight), new Color(210, 180, 140, 0));
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.gridx = session.getCurrentBatch().getFields().get(j).getXcoord();
				gbc.gridy = (session.getCurrentBatch().getFirstYCoord() + session.getCurrentBatch().getRecordHeight()*i);
				gbc.weightx = 1.0;
				gbc.weighty = 1.0;
				rootPanel.add(rect, gbc);
			}
		}
		*/
		//rootPanel.revalidate();
		//this.add(rootPanel);
		this.repaint();
		
	}
	
	@SuppressWarnings("deprecation")
	private void destroy() {
		//this.setOpaque(true);
		//this.setBackground(Color.DARK_GRAY);
		//image = null;
		//imgPanel = null;
		
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		if (bufImage == null) {
			g.drawImage(bufImage, 0, 0, null); // see javadoc for more info on the parameters            
		} else {
			/*
			image = bufImage.getScaledInstance(bufImage.getWidth()+session.getZoomLevel()*30, bufImage.getHeight()+session.getZoomLevel()*30, Image.SCALE_DEFAULT);
			g.drawImage(image, (this.getWidth() - image.getWidth(null))/2, (this.getHeight()-image.getHeight(null))/2, null); // see javadoc for more info on the parameters            
			*/
			Graphics2D g2 = (Graphics2D)g;
			drawShapes(g2);
		}
		
	}
	
	private void drawShapes(Graphics2D g2) {
		for (DrawingShape shape : shapes) {
			shape.draw(g2);
		}
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
		this.repaint();
		
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
		img = new JLabel(new ImageIcon(bufImage));
	}
	

	private MouseAdapter mouseAdapter = new MouseAdapter() {

		@Override
		public void mouseDragged(MouseEvent e) {
			
			int dx = e.getX() - (int)lastPoint.getX();
			int dy = e.getY() - (int)lastPoint.getY();
			
			for (DrawingShape s : dragShapes) {
				s.adjustPosition(dx, dy);
			}
			
			lastPoint = new Point2D.Double(e.getX(), e.getY());
			
			ImagePanel.this.repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			dragShapes.clear();
			
			for (DrawingShape s : shapes) {
				if (s.contains((Graphics2D)ImagePanel.this.getGraphics(), e.getX(), e.getY())) {
					dragShapes.add(s);
					System.out.println("CLICKED");
				}
			}
			
			lastPoint = new Point2D.Double(e.getX(), e.getY());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			dragShapes.clear();
			lastPoint = null;
		}
	};
}


