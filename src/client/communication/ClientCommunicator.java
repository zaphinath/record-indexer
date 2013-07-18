/**
 * 
 */
package client.communication;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import shared.communication.*;
import client.ClientException;

/**
 * @author zaphinath
 *
 */
public class ClientCommunicator {
	
	private static String SERVER_HOST = "localhost";
	private static int SERVER_PORT = 39640;
	private static String URL_PREFIX = "http://" + SERVER_HOST + ":" + SERVER_PORT;
	private static String HTTP_POST = "POST";
	
	private XStream  xmlStream;
	
	public ClientCommunicator () {
		xmlStream = new XStream(new DomDriver());
	}
	/**
	 * Validates the users login credentials 
	 * @param params The validate user params will contain the username and password
	 * @return ValidateUser_Result
	 * @throws ClientException if it fails for any reason other than non authentication
	 */
	public ValidateUser_Result validateUser(ValidateUser_Params params) throws ClientException{
		return (ValidateUser_Result) doPost("/ValidateUser", params);
	}
	
	/**
	 * Returns information about available Projects
	 * @param params A GetProject_Params object
	 * @return GetProjects_Result 
	 * 
	 * @throws ClientException if it fails for any reason
	 */
	public GetProjects_Result getProjects(GetProjects_Params params) throws ClientException {
		return (GetProjects_Result) doGet("/GetProjects");
	}
	
	/**
	 * Returns a sample image for the project
	 * @param params GetSampleImage_Params 
	 * @return GetSampleImage_Result The image URL else false if it fails for any reason
	 */
	public GetSampleImage_Result getSampleImage(GetSampleImage_Params params) throws ClientException {
		return null;
	}
	
	/**
	 * Downloads a batch for the user to index
	 * Server should assign the user a batch from the requested project.
	 * Server should not return batches that are already assigned to another user.
	 * If the user already has a batch assigned to them, this operation should fail 
	 * (i.e., a user is not allowed to have multiple batches assigned to them at the same time)
	 * @param params
	 * @return DownloadBatch_Result Result of batch information
	 * 
	 * @throws ClientException
	 */
	public DownloadBatch_Result downloadBatch(DownloadBatch_Params params) throws ClientException{
		return null;
	}
	
	/**
	 * Submits the indexed record field values for a batch to the Server
	 * Server unassigns user from submitted batch
	 * Server increments total number of records indexed by the user
	 * @param params SubmitBatch_Params "String username, String password, int batch, String recordValues"
	 * @return SubmitBatch_Result True if operation succeeds else false fails for any reason
	 * @throws ClientException if fails for any reason
	 */
	public SubmitBatch_Result submitBatch(SubmitBatch_Params params) throws ClientException{
		return null;
	}
	
	/**
	 * Returns information about all of the fields for the specified project
	 * If no project is specified, returns information about all of the fields for all projects in the system
	 * @param params GetFields_Params "String username, String password, int projectID"
	 * @return GetFields_Result 
	 * 
	 * @throws ClientException
	 */
	public GetFields_Result getFields(GetFields_Params params) throws ClientException {
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
	 * @param params Search_Params "String username, String password, String fields, String searchValues"
	 * @return Search_Result search results
	 * 
	 * @throws ClientException
	 */
	public Search_Result search(Search_Params params) throws ClientException {
		return null;
	}
	
	/**
	 * Make HTTP GET request to the specified URL
	 * and return the object returned by the server
	 * @param urlPath
	 * @return HTTP Get Object
	 * @throws ClientException
	 */
	private Object doGet(String urlPath) throws ClientException {
		return null;
	}
	
	/**
	 * Make HTTP POST request to the specified URL
	 * passing in the specified postData object
	 * @param urlPath
	 * @param postData
	 * @throws ClientException
	 */
	private Object doPost(String urlPath, Object postData) throws ClientException {
		try {
			URL url = new URL(URL_PREFIX + urlPath);
			System.out.println(url.toString());
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod(HTTP_POST);
			connection.setDoOutput(true);
			connection.connect();
			xmlStream.toXML(postData, connection.getOutputStream());
			connection.getOutputStream().close();
			System.out.println("HERE");
			//System.out.println("MADE HERE "+connection.getResponseCode());
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				Object result = xmlStream.fromXML(connection.getInputStream());
				return result;
			} else {
				throw new ClientException();//(String format("doPost failed % (htoop cod %d)", urlPath, connection.getResponseCode()));
			}
		} catch (IOException e) {
			throw new ClientException();
		}
		
	}
	
}
