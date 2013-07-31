/**
 * 
 */
package client.frame;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class ValidationFail extends JDialog {
	
	public ValidationFail() {
		JOptionPane.showMessageDialog(this,
		    "Username or password is invalid.",
		    "Inane error",
		    JOptionPane.ERROR_MESSAGE);
	}
}
