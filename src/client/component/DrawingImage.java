/**
 * 
 */
package client.component;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

import client.Session;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class DrawingImage extends JComponent implements DrawingShape {

	private Image image;
	private Rectangle2D rect;
	private Session session;
	private JPanel panel;
	
	public DrawingImage(Image image, Rectangle2D rect) {
		this.image = image;
		this.rect = rect;
	}
	
	public DrawingImage(Image image, Rectangle2D rect, Session session, JPanel panel) {
		this.image = image;
		this.rect = rect;
		this.session = session;
		this.panel = panel;
	}

	@Override
	public Object getObject() {
		return this.image;
	}
	
	@Override
	public void setObject(Object image) {
		this.image = (Image) image;
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
		/*Rectangle2D bounds = rect.getBounds2D();
		//g2.drawImage(image, (int)bounds.getMinX(), (int)bounds.getMinY(), (int)bounds.getMaxX(), (int)bounds.getMaxY(),
			//			0, 0, image.getWidth(null), image.getHeight(null), null);
		Image timage = image.getScaledInstance(image.getWidth(null)+session.getZoomLevel()*30, image.getHeight(null)+session.getZoomLevel()*30, Image.SCALE_DEFAULT);
		g2.drawImage(timage, (panel.getWidth() - timage.getWidth(null))/2, (panel.getHeight()-timage.getHeight(null))/2, null);
		*/
		g2.drawImage(image, (int)rect.getMinX(), (int)rect.getMinY(), (int)rect.getMaxX(), (int)rect.getMaxY(),
				0, 0, image.getWidth(null), image.getHeight(null), null);
	}
	
	@Override
	public int getWidth() {
		return image.getWidth(null);
	}
	
	@Override
	public int getHeight() {
		return image.getHeight(null);
	}
	
	

}
