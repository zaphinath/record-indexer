/**
 * 
 */
package shared.communication;

/**
 * @author zaphinath
 *
 */
public class GetFields_Params extends ValidateUser_Params {

	private int projectID;
	
	/**
	 * Class Constructor with parameters
	 * @param username
	 * @param password
	 * @param projectID
	 */
	public GetFields_Params(String username, String password, int projectID) {
		super.setUsername(username);
		super.setPassword(password);
		this.projectID = projectID;
	}
	
	/**
	 * Class Constructor
	 */
	public GetFields_Params() {
		
	}

	/**
	 * @return the projectID
	 */
	public int getProjectID() {
		return projectID;
	}

	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	
	
}
