/**
 * 
 */
package client.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
import client.component.ImageComponent;
import client.model.Cell;
import client.panel.MenuButtons;
import client.panel.SouthEast;
import client.panel.SouthWest;
import client.process.SaveSession;


/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class Indexer extends JFrame implements SessionListener{
	private JFrame thisFrame = this;
	private JMenuItem downloadBatch;
	private JMenuItem logout;
	private JMenuItem exit;
	private Session session;
	
	private JButton tableEntry;
	private JButton formEntry;
	private JButton fieldHelp;
	private JButton imageNav;
	
	private JPanel southEast;
	JSplitPane splitPane;
	JSplitPane splitSouth;
	
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
		session.addListener(this);
		session.setFrameWidth(session.getFrameWidth());
		session.setFrameHeight(session.getFrameHeight());
		this.setSize(session.getFrameWidth(), session.getFrameHeight());
		//setLocationRelativeTo(null);
		
		
		this.setLocation(session.getFramePoint());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		downloadBatch = new JMenuItem("Download Batch");
		mnFile.add(downloadBatch);
		downloadBatch.addActionListener(actionListener);
		System.out.println(session.isHaveBatch() + "Batch status");
		if (session.isHaveBatch())
			downloadBatch.setEnabled(false);
		
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
		JPanel southWest = new SouthWest(this, session);
		southEast = new SouthEast(session);
		
		
		JPanel menuButtons = new MenuButtons(this, session);
		//JPanel imagePanel = new ImagePanel(session);
		ImageComponent imagePanel = new ImageComponent(session);

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//, imagePanel, southPanel);
		splitPane.add(northPanel, JSplitPane.TOP);
		splitPane.add(southPanel, JSplitPane.BOTTOM);
		splitPane.setOneTouchExpandable(true);
		splitPane.setTopComponent(imagePanel);
		splitPane.setBottomComponent(southPanel);	
		splitPane.setDividerLocation(session.getHorizontalDivider());
		splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent pce) {
			    session.setHorizontalDivider((Integer) pce.getNewValue());
			}
		});
		
		northPanel.add(menuButtons, BorderLayout.WEST);
		
		splitSouth = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitSouth.add(southWest);
		splitSouth.add(southEast);
		splitSouth.setOneTouchExpandable(true);
		splitSouth.setLeftComponent(southWest);
		splitSouth.setRightComponent(southEast);
		splitSouth.setDividerLocation(session.getVerticalDivider());
		splitSouth.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent pce) {
			    session.setVerticalDivider((Integer) pce.getNewValue());
			}
		});
		
		southPanel.add(splitSouth);

		rootPanel.add(northPanel, BorderLayout.NORTH);
		rootPanel.add(splitPane, BorderLayout.CENTER);
		
		this.add(rootPanel);
		
	}
	
	private ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == downloadBatch) {
				addDownload();
			} else if (e.getSource() == logout) {
				SaveSession save = new SaveSession(thisFrame, session);
				save.writeFile();
				userLogin = new UserValidation(session.getHost(), session.getPort());
				userLogin.setVisible(true);
				dis();
				//System.exit(-9);
			} else if (e.getSource() == exit) {
				SaveSession save = new SaveSession(thisFrame, session);
				save.writeFile();
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
		this.session = null;
		this.setVisible(false);
		this.dispose();
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		if (session.isHaveBatch()) {
			downloadBatch.setEnabled(false);
		} else {
			//southEast = new SouthEast(session);
			downloadBatch.setEnabled(true);
		}
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#valueChanged(client.model.Cell, java.lang.String)
	 */
	@Override
	public void valueChanged(Cell cell, String newValue) {
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#selectedCellChanged(client.model.Cell)
	 */
	@Override
	public void selectedCellChanged(Cell newSelectedCell) {
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#toggleHighlightsChanged(boolean)
	 */
	@Override
	public void toggleHighlightsChanged(boolean toggle) {
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#imageInversionChanged(boolean)
	 */
	@Override
	public void imageInversionChanged(boolean inversion) {
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#scaleChanged(double)
	 */
	@Override
	public void scaleChanged(double scale) {
		
	}
	
	
}
