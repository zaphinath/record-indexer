/**
 * 
 */
package shared.communication;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import shared.model.Field;

/**
 * @author zaphinath
 *
 */
public class DownloadBatch_Result {
	
	private int batchId;
	private int projectId;
	private URL imageUrl;
	private int firstYCoord;
	private int recordHeight;
	private int numRecords;
	private int numFields;
	private String urlPrefix;
	
	List<Field> fields;
	List<Fields> newFields;
	
	private class Fields {
		public int fieldId;
		public int fieldNum;
		public String fieldTitle;
		public URL helpUrl;
		public int xCoord;
		public int pixelWidth;
		public URL knownValues;

		
	}
	
	public DownloadBatch_Result() {
		
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
	 * @return the firstYCoord
	 */
	public int getFirstYCoord() {
		return firstYCoord;
	}

	/**
	 * @param firstYCoord the firstYCoord to set
	 */
	public void setFirstYCoord(int firstYCoord) {
		this.firstYCoord = firstYCoord;
	}

	/**
	 * @return the recordHeight
	 */
	public int getRecordHeight() {
		return recordHeight;
	}

	/**
	 * @param recordHeight the recordHeight to set
	 */
	public void setRecordHeight(int recordHeight) {
		this.recordHeight = recordHeight;
	}

	/**
	 * @return the numRecords
	 */
	public int getNumRecords() {
		return numRecords;
	}

	/**
	 * @param numRecords the numRecords to set
	 */
	public void setNumRecords(int numRecords) {
		this.numRecords = numRecords;
	}

	/**
	 * @return the numFields
	 */
	public int getNumFields() {
		return numFields;
	}

	/**
	 * @param numFields the numFields to set
	 */
	public void setNumFields(int numFields) {
		this.numFields = numFields;
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
	 * @return the fields
	 */
	public List<Field> getFields() {
		return fields;
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

	/**
	 * This should only be fields that match the project
	 * and batchid's
	 * @param fields the fields to set
	 * @throws MalformedURLException 
	 */
	public void setFields(List<Field> fields) throws MalformedURLException {
		this.fields = fields;
		this.newFields = new ArrayList<Fields>();
		this.numFields = fields.size();
		for (int i = 0; i < numFields; i++) {
			newFields.get(i).fieldId = fields.get(i).getId();
		//TODO: field_num	newFields.get(i).fieldNum = fields.get(i).g
			newFields.get(i).fieldTitle = fields.get(i).getTitle();
			newFields.get(i).helpUrl = new URL(urlPrefix + fields.get(i).getHtmlHelp());
			newFields.get(i).xCoord = fields.get(i).getXcoord();
			newFields.get(i).pixelWidth = fields.get(i).getWidth();
			if (fields.get(i).getKnownData() != null) {
				newFields.get(i).knownValues = new URL(urlPrefix + fields.get(i).getKnownData());
			} else {
				newFields.get(i).knownValues = null;
			}
		}
		
	}

	@Override 
	public String toString() {
		String tmp = batchId + "\n" +
								 projectId + "\n" +
								 imageUrl + "\n" +
								 firstYCoord + "\n" + 
								 recordHeight + "\n" +
								 numRecords + "\n" +
								 numFields + "\n";
		//Need to loop through all fields according to spec
		for (int i = 0; i < numFields; i++) {
			tmp = newFields.get(i).fieldId + "\n" +
						newFields.get(i).fieldNum + "\n" +
						newFields.get(i).fieldTitle + "\n" +
						newFields.get(i).helpUrl.toString() + "\n" +
						newFields.get(i).xCoord + "\n" +
						newFields.get(i).pixelWidth + "\n";
			if (newFields.get(i).knownValues != null) {
				tmp = newFields.get(i).knownValues + "\n";
			}
		}
		return tmp;
	}

}
