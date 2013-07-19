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
	private String firstName;
	private String lastName;
	private String indexedRecords;
	


	public ValidateUser_Result() {
		this.bool = "FALSE\n";
		//this.firstName = null;
		//this.lastName = null;
		//this.indexedRecords = null;
	}
	
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		if (firstName != null) {
			return bool +
					firstName +
					lastName +
					indexedRecords;
		} else {
			return bool;
		}
	}

}
