/**
 * 
 */
package client.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

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
	private int w_centerX;
	private int w_centerY;
	private double scale;
	
	private boolean dragging;
	private int w_dragStartX;
	private int w_dragStartY;
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
		shapes = new ArrayList<DrawingShape>();
		stroke = new BasicStroke(5);

		Image image = null;
		if (session.isHaveBatch()) {
			//System.out.println(session.getImageUrl()+"URL");
			image = loadImage(session.getImageUrl());
			shapes.add(new DrawingImage(image, new Rectangle2D.Double(0, 0, image.getWidth(null), image.getHeight(null)), session));
			
			/*JPanel rootPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			
			for (int i=0; i < session.getNumRecords(); i++) {
				for (int j=0; j < session.getNumFields()-1; j++) {
					int tmpHeight = session.getRecordHeight();
					int tmpWidth = session.getFields().get(j).getWidth();
					double tmpX = 0;
					double tmpY = 0;
					//DrawingRect rect = new DrawingRect(new Rectangle2D.Double(tmpX, tmpY, tmpWidth, tmpHeight), new Color(210, 180, 140, 0));
					JPanel tmp = new JPanel();
					tmp.setSize(new Dimension(tmpWidth, tmpHeight));
					tmp.setBackground(Color.BLUE);
					tmp.setForeground(Color.BLUE);
					gbc.fill = GridBagConstraints.HORIZONTAL;
					gbc.gridx = session.getFields().get(j).getXcoord();
					gbc.gridy = (session.getFirstYCoord() + session.getRecordHeight()*i);
					gbc.weightx = 1.0;
					gbc.weighty = 1.0;
					rootPanel.add(tmp, gbc);
				}
			}
			
			//rootPanel.revalidate();
			this.add(rootPanel);*/
		}
		
		w_originX = session.getW_originX();
		w_originY = session.getW_originY();
		//w_centerX = session.getW_centerX();
		//w_centerY = session.getW_centerY();
		w_centerX = session.getFrameWidth()/4;
		w_centerY = session.getFrameHeight()/20;
		session.setW_centerX(w_centerX);
		session.setW_centerY(w_centerY);
		scale = session.getScale();
		
		initDrag();
	
		
		this.setBackground(new Color(84,84,84));
		this.setPreferredSize(new Dimension(700, 700));
		this.setMinimumSize(new Dimension(100, 100));
		this.setMaximumSize(new Dimension(1000, 1000));
		
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		//this.addComponentListener(componentAdapter);			
	}

	private void initDrag() {
		dragging = false;
		w_dragStartX = 0;
		w_dragStartY = 0;
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
		//System.out.println("X: " + w_centerX + " Y: " + w_centerY);
		g2.translate(w_centerX, w_centerY);
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
			
			AffineTransform transform = new AffineTransform();
			transform.translate(w_centerX, w_centerY);
			transform.scale(scale, scale);
			transform.translate(-w_originX, -w_originY);
			
			Point2D d_Pt = new Point2D.Double(d_X, d_Y);
			Point2D w_Pt = new Point2D.Double();
			try
			{
				transform.inverseTransform(d_Pt, w_Pt);
			}
			catch (NoninvertibleTransformException ex) {
				return;
			}
			int w_X = (int)w_Pt.getX();
			int w_Y = (int)w_Pt.getY();
			
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
				w_dragStartX = w_X;
				w_dragStartY = w_Y;		
				w_dragStartOriginX = w_originX;
				w_dragStartOriginY = w_originY;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {		
			if (dragging) {
				int d_X = e.getX();
				int d_Y = e.getY();
				
				AffineTransform transform = new AffineTransform();
				transform.translate(w_centerX, w_centerY);
				transform.scale(scale, scale);
				transform.translate(-w_dragStartOriginX, -w_dragStartOriginY);
				
				Point2D d_Pt = new Point2D.Double(d_X, d_Y);
				Point2D w_Pt = new Point2D.Double();
				try
				{
					transform.inverseTransform(d_Pt, w_Pt);
				}
				catch (NoninvertibleTransformException ex) {
					return;
				}
				int w_X = (int)w_Pt.getX();
				int w_Y = (int)w_Pt.getY();
				
				int w_deltaX = w_X - w_dragStartX;
				int w_deltaY = w_Y - w_dragStartY;
				
				w_originX = w_dragStartOriginX - w_deltaX;
				w_originY = w_dragStartOriginY - w_deltaY;
				
				//notifyOriginChanged(w_originX, w_originY);
				
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
			shapes.add(new DrawingImage(image, new Rectangle2D.Double(0, 0, image.getWidth(null), image.getHeight(null)), session));
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
		//invertImage(session.getImageUrl());
		//System.out.println("FIRST " + inversion);
		repaint();

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
