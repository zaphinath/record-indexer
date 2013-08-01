/**
 * 
 */
package client.frame;

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
	private GetSampleImage_Result result;
	private Image image;
	//TODO: Make modal and not resizable
	public SampleImage(ClientCommunicator cc, GetSampleImage_Params params, String project) {
		super();
		try {
			result = cc.getSampleImage(params);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setModal(true);
		this.setTitle("Sample image from "+project);
		this.setSize(600, 400);
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
