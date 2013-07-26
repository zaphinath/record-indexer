/**
 * 
 */
package client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

/**
 * @author zaphinath
 *
 */
public class UserValidation extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	
	public UserValidation() {
		super();
		intializeComponent();
	}

	/**
	 * 
	 */
	private void intializeComponent() {
		JFrame frame = new JFrame("Login to Indexer");
		
		JLabel userLabel = new JLabel("Username:");
		JLabel passLabel = new JLabel("Password:");
		
		userLabel.setBounds(10, 10, 100, 30);
		passLabel.setBounds(10, 50, 100, 30);
		
		username = new JTextField();
		username.setBounds(120, 10, 240, 30);
		
		password = new JPasswordField();
		password.setBounds(120, 50, 240, 30);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(120, 90, 90, 30);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(210, 90, 90, 30);
		
		frame.getContentPane().setLayout(null);
		frame.setSize(380, 150);
		frame.getContentPane().add(userLabel);
		frame.getContentPane().add(passLabel);
		frame.getContentPane().add(username);
		frame.getContentPane().add(password);
		frame.getContentPane().add(btnLogin);
		frame.getContentPane().add(btnExit);
		
	}
}
