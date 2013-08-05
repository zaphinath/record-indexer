/**
 * 
 */
package client.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import client.Session;
import client.SessionListener;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class ImageComponent extends JComponent implements SessionListener {
	private static Image NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
	
	private Session session;
	private int w_originX;
	private int w_originY;
	private double scale;
	
	private boolean dragging;
	private int d_dragStartX;
	private int d_dragStartY;
	private int w_dragStartOriginX;
	private int w_dragStartOriginY;

	private ArrayList<DrawingShape> shapes;
	private BasicStroke stroke;
	
	/**
	 * 
	 */
	public ImageComponent(Session session) {
		super();
		this.session = session;
		session.addListener(this);
		
		w_originX = session.getW_originX();
		w_originY = session.getW_originY();
		scale = session.getScale();
		
		initDrag();
	
		shapes = new ArrayList<DrawingShape>();
		stroke = new BasicStroke(5);
		
		this.setBackground(new Color(84,84,84));
		this.setPreferredSize(new Dimension(700, 700));
		this.setMinimumSize(new Dimension(100, 100));
		this.setMaximumSize(new Dimension(1000, 1000));
		
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		//this.addComponentListener(componentAdapter);
		
		if (session.isHaveBatch()) {
			Image image = loadImage(session.getImageUrl());
			shapes.add(new DrawingImage(image, new Rectangle2D.Double(0, 0, image.getWidth(null), image.getHeight(null))));//, session, this));
		}
	}

	private void initDrag() {
		dragging = false;
		d_dragStartX = 0;
		d_dragStartY = 0;
		w_dragStartOriginX = 0;
		w_dragStartOriginY = 0;
	}
	
	private Image loadImage(URL url) {
		try {
			return ImageIO.read(url);
		}
		catch (IOException e) {
			return NULL_IMAGE;
			//return null;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		drawBackground(g2);
		
		g2.translate(session.getFramePoint().x, session.getFramePoint().y);
		g2.scale(scale, scale);
		g2.translate(-w_originX, -w_originY);
		
		drawShapes(g2);
	}
	
	private void drawBackground(Graphics2D g2) {
		g2.setColor(getBackground());
		g2.fillRect(0,  0, getWidth(), getHeight());
	}

	private void drawShapes(Graphics2D g2) {
		for (DrawingShape shape : shapes) {
			shape.draw(g2);
		}
	}
	
	private MouseAdapter mouseAdapter = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {
			int d_X = e.getX();
			int d_Y = e.getY();
			int w_X = deviceToWorldX(d_X);
			int w_Y = deviceToWorldY(d_Y);
			
			boolean hitShape = false;
			
			Graphics2D g2 = (Graphics2D)getGraphics();
			for (DrawingShape shape : shapes) {
				if (shape.contains(g2, w_X, w_Y)) {
					hitShape = true;
					break;
				}
			}
			
			if (hitShape) {
				dragging = true;		
				d_dragStartX = e.getX();
				d_dragStartY = e.getY();		
				w_dragStartOriginX = w_originX;
				w_dragStartOriginY = w_originY;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {		
			if (dragging) {
				int d_deltaX = (e.getX() - d_dragStartX);
				int d_deltaY = (e.getY() - d_dragStartY);
				
				int w_deltaX = (int)(d_deltaX / scale);
				int w_deltaY = (int)(d_deltaY / scale);
				
				w_originX = w_dragStartOriginX - w_deltaX;
				w_originY = w_dragStartOriginY - w_deltaY;
				
				repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			initDrag();
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			return;
		}	
	};
	
	private int worldToDeviceX(int w_X) {
		double d_X = w_X;
		d_X -= w_originX;
		d_X *= scale;
		return (int)d_X;
	}
	
	private int worldToDeviceY(int w_Y) {
		double d_Y = w_Y;
		d_Y -= w_originY;
		d_Y *= scale;
		return (int)d_Y;
	}
	
	private int deviceToWorldX(int d_X) {
		double w_X = d_X;
		w_X *= 1.0 / scale;
		w_X += w_originX;
		return (int)w_X;
	}
	
	private int deviceToWorldY(int d_Y) {
		double w_Y = d_Y;
		w_Y *= 1.0 / scale;
		w_Y += w_originY;
		return (int)w_Y;
	}

	
	public void setScale(double newScale) {
		scale = newScale;
		this.repaint();
	}
	
	public void setOrigin(int w_newOriginX, int w_newOriginY) {
		w_originX = w_newOriginX;
		w_originY = w_newOriginY;
		this.repaint();
	}
	
	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		if (session.isHaveBatch()) {
			Image image = loadImage(session.getImageUrl());
			shapes.add(new DrawingImage(image, new Rectangle2D.Double(0, 0, image.getWidth(null), image.getHeight(null))));//, session, this));
		} else {
			shapes.clear();
		}
		this.repaint();
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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see client.SessionListener#scaleChanged(double)
	 */
	@Override
	public void scaleChanged(double scale) {
		// TODO Auto-generated method stub
		this.scale = scale;
		repaint();
	}

}
