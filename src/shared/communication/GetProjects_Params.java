/**
 * 
 */
package shared.communication;

/**
 * @author zaphinath
 *
 */
public class GetProjects_Params extends ValidateUser_Params {

	/**
	 * Class Constructor with parameters 
	 * @param username Sets the super class username
	 * @param password Sets the super class password
	 */
	public GetProjects_Params(String username, String password) {
		super.setUsername(username);
		super.setPassword(password);
	}
	
	/**
	 * Class Constructor
	 */
	public GetProjects_Params() {
		
	}
}
