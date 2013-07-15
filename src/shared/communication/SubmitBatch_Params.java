/**
 * 
 */
package shared.communication;

/**
 * @author zaphinath
 *
 */
public class SubmitBatch_Params extends ValidateUser_Params {

	private int batchID;
	private String recordValues;
	
	/**
	 * Class Constructor with params
	 * @param username
	 * @param password
	 * @param batchID
	 * @param recordValues
	 */
	public SubmitBatch_Params(String username, String password, int batchID, String recordValues) {
		super.setUsername(username);
		super.setPassword(password);
		this.batchID = batchID;
		this.recordValues = recordValues;
	}
	
	/**
	 * Class Constructor
	 */
	public SubmitBatch_Params() {
		
	}

	/**
	 * @return the batchID
	 */
	public int getBatchID() {
		return batchID;
	}

	/**
	 * @param batchID the batchID to set
	 */
	public void setBatchID(int batchID) {
		this.batchID = batchID;
	}

	/**
	 * @return the recordValues
	 */
	public String getRecordValues() {
		return recordValues;
	}

	/**
	 * @param recordValues the recordValues to set
	 */
	public void setRecordValues(String recordValues) {
		this.recordValues = recordValues;
	}
	
}
