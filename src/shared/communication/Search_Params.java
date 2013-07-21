/**
 * 
 */
package shared.communication;

/**
 * @author zaphinath
 *
 */
public class Search_Params extends ValidateUser_Params {

	String fieldIds;
	String searchValues;
	String urlPrefix;
	
	/**
	 * Class Constructor with params
	 * @param username
	 * @param password
	 * @param fieldIds
	 * @param searchValues
	 */
	public Search_Params(String username, String password, String fieldIds, String searchValues) {
		super.setUsername(username);
		super.setPassword(password);
		this.fieldIds = fieldIds;
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
	public String getFieldIds() {
		return fieldIds;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFieldIds(String fieldIds) {
		this.fieldIds = fieldIds;
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
