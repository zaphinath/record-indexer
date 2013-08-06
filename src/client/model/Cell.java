/**
 * 
 */
package client.model;

/**
 * @author Derek Carr
 *
 */
public class Cell {
	private int field;
	private int record;
	
	/**
	 * @param record
	 * @param field
	 */
	public Cell(int field, int record) {
		super();
		this.record = record;
		this.field = field;
	}

	/**
	 * @return the record
	 */
	public int getRecord() {
		return record;
	}

	/**
	 * @param record the record to set
	 */
	public void setRecord(int record) {
		this.record = record;
	}

	/**
	 * @return the field
	 */
	public int getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(int field) {
		this.field = field;
	}
	
	
}
