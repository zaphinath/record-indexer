/**
 * 
 */
package client;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;

import client.frame.UserValidation;

/**
 * @author zaphinath
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 39640;
		String host = "localhost";
		if (args.length == 2) {
			host = args[0];
			port = Integer.parseInt(args[1]);
		}
		SwingUtilities.invokeLater(new Runnable() {		
			public void run() {
				UserValidation userValidator = new UserValidation();
				userValidator.setVisible(true);
			}
		});

	}

}
