/**
 * 
 */
package client.component;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 * @author zaphinath
 *
 */
public interface DrawingShape {
	public boolean contains(Graphics2D g2, double x, double y);
	public void adjustPosition(double dx, double dy);
	public void draw(Graphics2D g2);
	public int getWidth();
	public int getHeight();
	public Object getObject();
	public void setObject(Object object);
}
