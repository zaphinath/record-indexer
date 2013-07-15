/**
 * 
 */
package shared.communication;

/**
 * @author zaphinath
 *
 */
public class Search_Params extends ValidateUser_Params {

	String fields;
	String searchValues;
	
	/**
	 * Class Constructor with params
	 * @param username
	 * @param password
	 * @param fields
	 * @param searchValues
	 */
	public Search_Params(String username, String password, String fields, String searchValues) {
		super.setUsername(username);
		super.setPassword(password);
		this.fields = fields;
		this.searchValues = searchValues;
	}
	
	/**
	 * Class Constructor
	 */
	public Search_Params() {
		
	}

	/**
	 * @return the fields
	 */
	public String getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(String fields) {
		this.fields = fields;
	}

	/**
	 * @return the searchValues
	 */
	public String getSearchValues() {
		return searchValues;
	}

	/**
	 * @param searchValues the searchValues to set
	 */
	public void setSearchValues(String searchValues) {
		this.searchValues = searchValues;
	}
	
	
	
}
