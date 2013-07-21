/**
 * 
 */
package shared.communication;

import java.net.URL;
import java.util.List;

/**
 * @author zaphinath
 *
 */
public class Search_Result {
	
	private int projectId;
	private String fieldTitle;
	
	private int batchId;
	private URL imageUrl;
	private int recordNumber;
	private int fieldId;
	
	private List<Search_Result> list;
	
	public Search_Result() {
		this.projectId = -1;
		this.fieldId = -1;
		this.fieldTitle = null;
		this.list = null;
	}
	
	public Search_Result(List<Search_Result> list) {
		this.list = list;
	}

	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
	 * @return the fieldTitle
	 */
	public String getFieldTitle() {
		return fieldTitle;
	}

	/**
	 * @param fieldTitle the fieldTitle to set
	 */
	public void setFieldTitle(String fieldTitle) {
		this.fieldTitle = fieldTitle;
	}

	/**
	 * @return the list
	 */
	public List<Search_Result> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Search_Result> list) {
		this.list = list;
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
	 * @return the imageUrl
	 */
	public URL getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(URL imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the recordNumber
	 */
	public int getRecordNumber() {
		return recordNumber;
	}

	/**
	 * @param recordNumber the recordNumber to set
	 */
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	
	

}
