/**
 * 
 */
package shared.communication;

/**
 * @author zaphinath
 *
 */
public class ValidateUser_Params {
	
	private String username;
	private String password;
	
	/**
	 * Class Constructor with arguments 
	 * @param username Username
	 * @param password Password
	 */
	public ValidateUser_Params(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Class Constructor
	 */
	public ValidateUser_Params() {
		
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override 
	public String toString() {
		return this.username + "\n" + this.password + "\n";
	}
}
