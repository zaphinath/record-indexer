/**
 * 
 */
package client.frame;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import client.Session;
import client.panel.MenuButtons;


/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class Indexer extends JFrame {
	private JMenuItem txtDownloadBatch;
	private JMenuItem txtLogout;
	private JMenuItem txtExit;
	private Session session;
	
	/**
	 * Class Constructor
	 */
	public Indexer() {
		super();
		this.session = new Session();
		initialize();
	}
	
	public Indexer(Session s) {
		super();
		this.session = s;
		initialize();
	}
	
	private void initialize() {
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
		
		JPanel menuButtons = new MenuButtons(session);
	}

}
