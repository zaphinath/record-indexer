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

import client.ClientException;
import client.communication.ClientCommunicator;
import shared.communication.GetSampleImage_Params;
import shared.communication.GetSampleImage_Result;

/**
 * @author Derek Carr
 *
 */
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
		this.setSize(800, 600);
		this.setResizable(false);
		try {
			image = ImageIO.read(result.getImageUrl());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel img = new JLabel(new ImageIcon(image));
		this.add(img);
	}
}
