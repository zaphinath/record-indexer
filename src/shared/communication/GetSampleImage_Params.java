/**
 * 
 */
package shared.communication;

/**
 * @author zaphinath
 *
 */
public class GetSampleImage_Params extends ValidateUser_Params {

	private int projectID;
	
	/**
	 * Class Constructor with parameters 
	 * @param username
	 * @param password
	 * @param projectID
	 */
	public GetSampleImage_Params(String username, String password, int projectID) {
		super.setPassword(password);
		super.setUsername(username);
		this.projectID = projectID;
	}
	
	/**
	 * Class Constructor
	 */
	public GetSampleImage_Params() {
		
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
