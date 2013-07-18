/**
 * 
 */
package shared.communication;

/**
 * @author zaphinath
 *
 */
public class ValidateUser_Result {
	private String bool;
	private String username;
	private String password;
	private String indexedRecords;
	


	public ValidateUser_Result() {
		this.bool = "FALSE\n";
		//this.username = null;
		//this.password = null;
		//this.indexedRecords = null;
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


	/**
	 * @return the indexedRecords
	 */
	public String getIndexedRecords() {
		return indexedRecords;
	}


	/**
	 * @param indexedRecords the indexedRecords to set
	 */
	public void setIndexedRecords(String indexedRecords) {
		this.indexedRecords = indexedRecords;
	}


	/**
	 * @return the bool
	 */
	public String getBool() {
		return bool;
	}


	/**
	 * @param bool the bool to set
	 */
	public void setBool(String bool) {
		this.bool = bool;
	}


	@Override
	public String toString() {
		if (username != null) {
			return bool +
					username +
					password +
					indexedRecords;
		} else {
			return bool;
		}
	}

}
