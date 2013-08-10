/**
 * 
 */
package client.component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class DrawingRect extends JComponent implements DrawingShape {

	private Rectangle2D rect;
	private Color color;
	
	public DrawingRect(Rectangle2D rect, Color color) {
		this.rect = rect;
		this.color = color;
	}

	@Override
	public boolean contains(Graphics2D g2, double x, double y) {
		return rect.contains(x, y);
	}

	@Override
	public void adjustPosition(double dx, double dy) {
		rect.setRect(rect.getX() + dx, rect.getY() + dy, rect.getWidth(), rect.getHeight());	
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fill(rect);
		// OR g2.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
	}

	/* (non-Javadoc)
	 * @see client.component.DrawingShape#getObject()
	 */
	@Override
	public Object getObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.component.DrawingShape#setObject(java.lang.Object)
	 */
	@Override
	public void setObject(Object object) {
		// TODO Auto-generated method stub
		
	}

}
