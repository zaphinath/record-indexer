/**
 * 
 */
package client.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import client.ClientException;
import client.Session;
import client.SessionListener;
import client.model.Cell;
import client.panel.ImagePanel;
import client.panel.MenuButtons;
import client.panel.SouthEast;
import client.panel.SouthWest;
import client.panel.TableEntry;


/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class Indexer extends JFrame implements SessionListener{
	private JMenuItem downloadBatch;
	private JMenuItem logout;
	private JMenuItem exit;
	private Session session;
	
	private JButton tableEntry;
	private JButton formEntry;
	private JButton fieldHelp;
	private JButton imageNav;
	
	private UserValidation userLogin;
	private DownloadBatch dBatch;
	
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
	
	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	private void initialize() {
		session.setFrameWidth(1200);
		session.setFrameHeight(800);
		this.setSize(session.getFrameWidth(), session.getFrameHeight());
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		downloadBatch = new JMenuItem("Download Batch");
		mnFile.add(downloadBatch);
		downloadBatch.addActionListener(actionListener);
		
		logout = new JMenuItem("Logout");
		mnFile.add(logout);
		logout.addActionListener(actionListener);
		
		exit = new JMenuItem("Exit");
		mnFile.add(exit);
		exit.addActionListener(actionListener);
		
		tableEntry = new JButton("Table Entry");
		formEntry = new JButton("Form Entry");
		fieldHelp = new JButton("Field Help");
		imageNav = new JButton("Image Navigation");
		
		tableEntry.setFocusPainted(true);
		formEntry.setFocusPainted(true);
		
		tableEntry.addActionListener(actionListener);
		formEntry.addActionListener(actionListener);
		fieldHelp.addActionListener(actionListener);
		imageNav.addActionListener(actionListener);
		
		JPanel rootPanel = new JPanel(new BorderLayout());
		JPanel northPanel = new JPanel(new BorderLayout());
		JPanel southPanel = new JPanel(new BorderLayout());
		JPanel southWest = new SouthWest(session);
		JPanel southEast = new SouthEast(session);
		
		//JPanel swNorth = new JPanel();
		//JPanel swSouth = new JPanel();
		
		//JPanel seNorth = new JPanel();
		//JPanel seSouth = new JPanel();
		
		JPanel menuButtons = new MenuButtons(session);
		JPanel imagePanel = new ImagePanel(session);
		//JPanel tableEntryPanel = new TableEntry(session);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setTopComponent(imagePanel);
		splitPane.setBottomComponent(southPanel);
		//splitPane.setDividerLocation(150);
		
		//swNorth.add(tableEntry);
		//swNorth.add(formEntry);
		
		//seNorth.add(fieldHelp);
		//seNorth.add(imageNav);
		//seSouth.add(tableEntryPanel);
		
		//southWest.add(swNorth, BorderLayout.NORTH);
		//southWest.add(swSouth, BorderLayout.SOUTH);
		
		//southEast.add(seNorth, BorderLayout.NORTH);
		//southEast.add(seSouth, BorderLayout.SOUTH);
		
		northPanel.add(menuButtons, BorderLayout.WEST);
		southPanel.add(southWest, BorderLayout.WEST);
		southPanel.add(southEast, BorderLayout.EAST);
		
		rootPanel.add(northPanel, BorderLayout.NORTH);
		rootPanel.add(imagePanel, BorderLayout.CENTER);
		rootPanel.add(splitPane,BorderLayout.SOUTH);
		rootPanel.add(southPanel, BorderLayout.SOUTH);
		//rootPanel.add(splitPane);
		
		this.add(rootPanel);
	}
	
	private ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == downloadBatch) {
				addDownload();
			} else if (e.getSource() == logout) {
				//TODO: Save session and start indexer fresh
				userLogin = new UserValidation();
				userLogin.setVisible(true);
				dis();
				//System.exit(-9);
			} else if (e.getSource() == exit) {
				//TODO: Save session
				System.exit(-9);
			} else if  (e.getSource() == tableEntry) {
				
			} else if (e.getSource() == formEntry) {
				System.out.println("FORM");
			} else if (e.getSource() == fieldHelp) {
				
			} else if (e.getSource() == imageNav) {
				
			}
		}
	};
	
	private void addDownload() {
		try {
			dBatch = new DownloadBatch((JFrame)this, true, session);
			dBatch.setVisible(true);
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
	
	private void dis() {
		this.setVisible(false);
		this.dispose();
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#valueChanged(client.model.Cell, java.lang.String)
	 */
	@Override
	public void valueChanged(Cell cell, String newValue) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#selectedCellChanged(client.model.Cell)
	 */
	@Override
	public void selectedCellChanged(Cell newSelectedCell) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#zoomeLevelChanged(int)
	 */
	@Override
	public void zoomeLevelChanged(int zoom) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#toggleHighlightsChanged(boolean)
	 */
	@Override
	public void toggleHighlightsChanged(boolean toggle) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#imageInversionChanged(boolean)
	 */
	@Override
	public void imageInversionChanged(boolean inversion) {
		// TODO Auto-generated method stub
		
	}
	
	
}
