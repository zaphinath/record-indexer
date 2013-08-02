/**
 * 
 */
package client.frame;

import java.awt.Frame;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shared.communication.GetSampleImage_Result;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class SampleImage extends JDialog {
	private Image image;
	//TODO: Make modal and not resizable
	public SampleImage(Frame frame, GetSampleImage_Result result, String project) {
		super(frame, true);
		assert frame != null;
		assert result != null;
		assert project != null;	
				
		setLocationRelativeTo(null);
		this.setModal(true);
		this.setTitle("Sample image from "+project);
		this.setSize(600, 400);
		this.setResizable(false);
		try {
			image = ImageIO.read(result.getImageUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JPanel rootPanel = new JPanel();
		rootPanel.setSize(600, 400);
		JLabel img = new JLabel(new ImageIcon(image));
		rootPanel.add(img);
		this.add(rootPanel);
	}
}
