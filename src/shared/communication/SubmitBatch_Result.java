/**
 * 
 */
package shared.communication;

/**
 * @author zaphinath
 *
 */
public class SubmitBatch_Result {
	
	private boolean submitted;

	/**
	 * @return the submitted
	 */
	public boolean isSubmitted() {
		return submitted;
	}

	/**
	 * @param submitted the submitted to set
	 */
	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	} 
	
	@Override 
	public String toString() {
		String tmp = "";
		if (submitted) {
			tmp = "TRUE\n";
		} else {
			tmp = "FAILED\n";
		}
		return tmp;
	}
	
	
}
