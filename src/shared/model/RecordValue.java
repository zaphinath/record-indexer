/**
 * 
 */
package shared.model;

/**
 * @author Derek Carr
 *
 */
public class RecordValue {
	
	private int id;
	private int batchId;
	private int fieldId;
	private String value;
	
	/**
	 * Class Constructor
	 */
	public RecordValue() {
		this.id = -1;
		this.batchId = -1;
		this.fieldId = -1;
		this.value = null;
	}
	
	/**
	 * Class Constructor with params
	 * @param id
	 * @param batchId
	 * @param fieldId
	 * @param value
	 */
	public RecordValue(int id, int batchId, int fieldId, String value) {
		this.id = id;
		this.batchId = batchId;
		this.fieldId = fieldId;
		this.value = value;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the batchId
	 */
	public int getBatchId() {
		return batchId;
	}

	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	/**
	 * @return the fieldId
	 */
	public int getFieldId() {
		return fieldId;
	}

	/**
	 * @param fieldId the fieldId to set
	 */
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	
}
