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
	static int port = 39640;
	static String host = "localhost";
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length == 2) {
			host = args[0];
			port = Integer.parseInt(args[1]);
		}
		SwingUtilities.invokeLater(new Runnable() {		
			public void run() {
				UserValidation userValidator = new UserValidation(host, port);
				userValidator.setVisible(true);
			}
		});

	}

}
