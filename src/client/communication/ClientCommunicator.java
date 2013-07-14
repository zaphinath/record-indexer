/**
 * 
 */
package client.communication;

/**
 * @author zaphinath
 *
 */
public class ClientCommunicator {
	
	/**
	 * Validates the users login credentials 
	 * @param username
	 * @param password
	 * @return true if the user exists in the database
	 */
	public boolean validateUser(String username, String password) {
			return true;
	}
	
	/**
	 * Returns information about available Projects
	 * @param password
	 * @param username
	 */
	public void getProjects(String password, String username) {
		
	}
	
	/**
	 * Returns a sample image for the project
	 * @param username
	 * @param password
	 * @param projectID
	 * @return The image URL else false if it fails for any reason
	 */
	public String getSampleImage(String username, String password, int projectID) {
		String build = null;
		return build;
	}
	
	/**
	 * Downloads a batch for the user to index
	 * Server should assign the user a batch from the requested project.
	 * Server should not return batches that are already assigned to another user.
	 * If the user already has a batch assigned to them, this operation should fail 
	 * (i.e., a user is not allowed to have multiple batches assigned to them at the same time)
	 * @param username
	 * @param password
	 * @param projectID
	 * @return 
	 */
	public String downloadBatch(String username, String password, int projectID) {
		return null;
		
	}
	
	/**
	 * Submits the indexed record field values for a batch to the Server
	 * Server unassigns user from submitted batch
	 * Server increments total number of records indexed by the user
	 * @param username
	 * @param password
	 * @param batch
	 * @param recordValues
	 * @return true if operation succeeds else false fails for any reason
	 */
	public boolean submitBatch(String username, String password, int batch, String recordValues) {
		return true;
	}
	
	/**
	 * Returns information about all of the fields for the specified project
	 * If no project is specified, returns information about all of the fields for all projects in the system
	 * @param username
	 * @param password
	 * @param projectID
	 * @return
	 */
	public String getFields(String username, String password, int projectID) {
		return null;
	}
	
	
}
