/**
 * 
 */
package client;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class ClientException extends Exception {

	/**
	 * Class Constructor
	 */
	public ClientException() {
		return;
	}

	/**
	 * 
	 * @param message
	 */
	public ClientException(String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param throwable
	 */
	public ClientException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public ClientException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
