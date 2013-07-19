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
	private String urlPrefix;
	
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
	
	public GetSampleImage_Params(String username, String password) {
		super();
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

	/**
	 * @return the urlPrefix
	 */
	public String getUrlPrefix() {
		return urlPrefix;
	}

	/**
	 * @param urlPrefix the urlPrefix to set
	 */
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	
	
}
