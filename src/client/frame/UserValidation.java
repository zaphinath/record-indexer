/**
 * 
 */
package client.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.KeyStroke;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import shared.communication.*;
import client.ClientException;
import client.Session;
import client.communication.ClientCommunicator;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class UserValidation extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private Indexer indexer;
	private int port;// = 39640;
	private String host;// = "localhost";
	private XStream xmlStream;
	private Session s;
	
	/*public UserValidation() {
		super();
		xmlStream = new XStream(new DomDriver());
		intializeComponent();

		
	}*/

	/**
	 * @param host2
	 * @param port2
	 */
	public UserValidation(String host2, int port2) {
		super();
		this.host = host2;
		this.port = port2;
		xmlStream = new XStream(new DomDriver());
		intializeComponent();
	}

	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
	private void intializeComponent() {
		this.setSize(500, 350);
		//JFrame frame = new JFrame("Login to Indexer");
		this.setTitle("Login to Indexer");

		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel userLabel = new JLabel("Username:");
		JLabel passLabel = new JLabel("Password:");
		
		userLabel.setBounds(10, 10, 100, 30);
		passLabel.setBounds(10, 50, 100, 30);
		
		username = new JTextField();
		username.setBounds(120, 10, 240, 30);
		
		password = new JPasswordField();
		password.setBounds(120, 50, 240, 30);
		
		ActionListener submit = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//assert port != 0;
				//assert host !=null;
				String user = username.getText();
				String pass = password.getText();
				ClientCommunicator cc = new ClientCommunicator(host, port);
				//cc.setSERVER_PORT(port);
				//cc.setSERVER_HOST(host);
				System.out.println(cc.getSERVER_HOST() + cc.getSERVER_PORT());
				ValidateUser_Params params = new ValidateUser_Params(user,pass);
				ValidateUser_Result result = null;
				try {
					result = cc.validateUser(params);
				} catch (ClientException e1) {
					e1.printStackTrace();
				}
				if (result.getBool().toLowerCase().contains("true")) {
					//TODO: Check file exists. If not new session
					File file = new File("sessions/"+result.getLastName().toLowerCase().trim()+result.getFirstName().toLowerCase().trim()+".session");
					if (file.exists()) {
						System.out.println("Has File: ");
						//Session s =null;
						//ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
						//s = (Session) inputStream.readObject();
						//inputStream.close();
						s = (Session) xmlStream.fromXML(file);
						System.out.println(s.getBatchId()+ "reading id");
						s.initiliazeListenersList();
						s.setUrlPrefix("http://"+host+":"+port+"/files/");
						//System.out.println("URL PRE" + s.getUrlPrefix());
						indexer = new Indexer(s);
					} else {
						indexer = new Indexer();
					}
					indexer.getSession().setUsername(user);
					indexer.getSession().setPassword(pass);
					indexer.getSession().setFirstName(result.getFirstName().trim());
					indexer.getSession().setLastName(result.getLastName().trim());
					indexer.getSession().setHost(host);
					indexer.getSession().setPort(port);
					indexer.getSession().setUrlPrefix();
					indexer.getSession().setIndexedRecords(result.getIndexedRecords());
					System.out.println(indexer.getSession().getImageUrl());
					WindowAlert alert = new WindowAlert(indexer.getSession());
					alert.setVisible(true);
					
					indexer.setVisible(true);
					alert.dispose();
					dis();
				} else {
					ValidationFail fail = new ValidationFail();
					fail.setVisible(true);
					fail.dispose();
				}
				
				
			}
		};
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(submit);
		btnLogin.registerKeyboardAction(btnLogin.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);
		btnLogin.registerKeyboardAction(btnLogin.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
		btnLogin.setBounds(120, 90, 90, 30);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(-9);
			}
		});
		btnExit.setBounds(210, 90, 90, 30);
		
		this.getContentPane().setLayout(null);
		this.setSize(380, 150);
		this.getContentPane().add(userLabel);
		this.getContentPane().add(passLabel);
		this.getContentPane().add(username);
		this.getContentPane().add(password);
		this.getContentPane().add(btnLogin);
		this.getContentPane().add(btnExit);
		
		
	}
	
	/**
	 * 
	 */
	private void dis() {
		this.dispose();
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
}
