/**
 * 
 */
package client.frame;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import client.Session;

import shared.communication.ValidateUser_Result;

/**
 * @author Derek Carr
 *
 */
public class WindowAlert extends JDialog {
	
	public WindowAlert(ValidateUser_Result user) {
		JOptionPane.showMessageDialog(this, "Welcome, "+ user.getFirstName() + " " + user.getLastName()+"." + 
								"You have indexed " + user.getIndexedRecords() + "records.");
	}
	public WindowAlert(Session session) {
		
	}
}
