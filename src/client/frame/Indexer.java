/**
 * 
 */
package client.frame;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import client.panel.MenuButtons;

import java.awt.BorderLayout;
import javax.swing.JTextField;

/**
 * @author zaphinath
 *
 */
public class Indexer extends JFrame {
	private JMenuItem txtDownloadBatch;
	private JMenuItem txtLogout;
	private JMenuItem txtExit;
	
	/**
	 * Class Constructor
	 */
	public Indexer() {
		this.setSize(1200, 800);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		txtDownloadBatch = new JMenuItem();
		txtDownloadBatch.setText("Download Batch");
		mnFile.add(txtDownloadBatch);
		//txtDownloadBatch.setColumns(10);
		
		txtLogout = new JMenuItem();
		txtLogout.setText("Logout");
		mnFile.add(txtLogout);
		//txtLogout.setColumns(10);
		
		txtExit = new JMenuItem();
		txtExit.setText("Exit");
		mnFile.add(txtExit);
		
		JPanel menuButtons = new MenuButtons();
		
	}

}
