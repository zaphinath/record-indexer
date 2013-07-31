/**
 * 
 */
package client.model;

/**
 * @author Derek Carr
 *
 */
public class Cell {
	private int record;
	private int field;
	
	/**
	 * @param record
	 * @param field
	 */
	public Cell(int record, int field) {
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
