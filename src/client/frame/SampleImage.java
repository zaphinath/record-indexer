/**
 * 
 */
package client.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private BufferedImage image;
	private JButton ok;
	
	//TODO: Make modal and not resizable
	public SampleImage(Frame frame, GetSampleImage_Result result, String project) {
		super(frame, true);
		assert frame != null;
		assert result != null;
		assert project != null;	
				
		setLocationRelativeTo(null);
		this.setModal(true);
		this.setTitle("Sample image from "+project);
		this.setMinimumSize(new Dimension(600,450));
		//this.setSize(600, 400);
		this.setResizable(false);
		try {
			image = ImageIO.read(result.getImageUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image bImg = image.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
		JPanel rootPanel = new JPanel(new BorderLayout());
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
		//rootPanel.setSize(600, 400);
		
		ok = new JButton("Close");
		//ok.setLocation(280, 410);
		ok.addActionListener(actionListener);
		
		southPanel.add(Box.createRigidArea(new Dimension(280,0)));
		southPanel.add(ok);
		JLabel img = new JLabel(new ImageIcon(bImg));
		rootPanel.add(img, BorderLayout.NORTH);
		rootPanel.add(southPanel, BorderLayout.SOUTH);
		this.add(rootPanel);
	}
	
	private void destroy() {
		this.dispose();
	}
	private ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ok) {
				destroy();
			}
		}
	};
}
