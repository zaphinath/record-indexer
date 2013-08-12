/**
 * 
 */
package client.component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

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
	
	public DrawingImage(Image image, Rectangle2D rect) {
		this.image = image;
		this.rect = rect;
	}
	
	public DrawingImage(Image image, Rectangle2D rect, Session session) {
		this.image = image;
		this.rect = rect;
		this.session = session;
		//this.panel = panel;
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
		if (session.isImageInverted() == false ) {
			g2.drawImage(image, (int)rect.getMinX(), (int)rect.getMinY(), (int)rect.getMaxX(), (int)rect.getMaxY(),
				0, 0, image.getWidth(null), image.getHeight(null), null);
		} else {
			g2.drawImage(invertImage(session.getImageUrl()), (int)rect.getMinX(), (int)rect.getMinY(), (int)rect.getMaxX(), (int)rect.getMaxY(),
					0, 0, image.getWidth(null), image.getHeight(null), null);
		}
	}
	
	@Override
	public int getWidth() {
		return image.getWidth(null);
	}
	
	@Override
	public int getHeight() {
		return image.getHeight(null);
	}
	
	
	private Image invertImage(URL imageName) {
        
		//BufferedImage inputFile = (BufferedImage) input;
		BufferedImage inputFile = null;
        try {
            inputFile = ImageIO.read(imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < inputFile.getWidth(); x++) {
            for (int y = 0; y < inputFile.getHeight(); y++) {
                int rgba = inputFile.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                                255 - col.getGreen(),
                                255 - col.getBlue());
                inputFile.setRGB(x, y, col.getRGB());
            }
        }

        return (Image) inputFile;
    }
	

}
