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
	
	private List<Field> fields;
	
	public DownloadBatch_Result() {
		fields = new ArrayList<Field>();
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
		//String tmp = "FOO FIGHT";
		String tmp = batchId + "\n" +
								 projectId + "\n" +
								 imageUrl + "\n" +
								 firstYCoord + "\n" + 
								 recordHeight + "\n" +
								 numRecords + "\n" +
								 numFields + "\n";
		//Need to loop through all fields according to spec
		if ( fields != null) {
			for (int i = 0; i < numFields; i++) {
				tmp = tmp + fields.get(i).getId() + "\n" +
							fields.get(i).getId() + "\n" +
							fields.get(i).getTitle() + "\n" +
							urlPrefix + "files" + fields.get(i).getHtmlHelp() + "\n" +
							fields.get(i).getXcoord() + "\n" +
							fields.get(i).getWidth() + "\n";
				if (fields.get(i).getKnownData() != null) {
					tmp = tmp + urlPrefix + "files" + fields.get(i).getKnownData() + "\n";
				}
			}
		}
		return tmp;
	}

}
