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
	 * @return String of batch information
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
	 * @return string of fields
	 */
	public String getFields(String username, String password, int projectID) {
		return null;
	}
	
	/**
	 * Searches the indexed records for the specified strings
	 * The user specifies one or more fields to be searched, and one or more strings to search for. 
	 * The fields to be searched are specified by field ID. (Note,field IDs are unique across all fields in the system.)
	 * The Server searches all indexed records containing the specified fields for the specified strings, 
	 * and returns a list of all matches. In order to constitute a match, a value must appear in one of the search
	 * fields, and be exactly equal (ignoring case)to one of the search strings.
	 * 
	 * For each match found, the Server returns a tuple of the following form: (Batch ID, Image URL, Record Number, Field ID)
	 * <i>Batch ID</i> is the ID of the batch containing the match.
	 * <i>Image URL</i> is the URL of the batchs image file on the Server.
	 * <i>Record Number</i> is the number of the record (or row) on the batch that contains the match 
	 * (top-most record is number one, the one below it is number two, etc.).
	 * <i>Field ID</i> is the IDof the field in the record that contains the match 
	 * (this is the field's "ID", not its "number").
	 * 
	 * Intuitively, Search works by OR-ing the requirements together. 
	 * For example, if the user searches fields 1, 7 for values "a", "b", "c", 
	 * the result contains all matches for which the field is 1 OR 7 and the value is "a" OR "b" OR "c".
	 * @param username
	 * @param password
	 * @param fields
	 * @param searchValues
	 * @return string of search results
	 */
	public String search(String username, String password, String fields, String searchValues) {
		return null;
	}
	
	
}
