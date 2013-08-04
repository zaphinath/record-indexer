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
	
	public WindowAlert(Session s) {
		JOptionPane.showMessageDialog(this, "Welcome, "+ s.getFirstName() + " " + s.getLastName()+"." + 
								"You have indexed " + s.getIndexedRecords() + "records.");
	}
}
