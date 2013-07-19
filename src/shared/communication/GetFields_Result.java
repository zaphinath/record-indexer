/**
 * 
 */
package shared.communication;

import java.util.List;

import shared.model.Field;

/**
 * @author zaphinath
 *
 */
public class GetFields_Result {
	
	private List<Field> fields;

	/**
	 * @return the fields
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	@Override
	public String toString() {
		String tmp = "FAILED\n";
		if (fields != null) {
			tmp = "";
			for (int i = 0; i < fields.size(); i++) {
				tmp = tmp + fields.get(i).getProjectId() + "\n" +
									  fields.get(i).getId() + "\n" +
									  fields.get(i).getTitle() + "\n";
			}
		}
		return tmp;
	}

}
