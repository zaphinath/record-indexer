package shared.model;

/**
 * @author Derek Carr
 *
 */


public class Project {

	private int id;
	private String title;
	private int recordsPerImage;
	private int firstYCoord;
	private int recordHeight;
	
	public Project() {
		
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the recordsPerImage
	 */
	public int getRecordsPerImage() {
		return recordsPerImage;
	}

	/**
	 * @param recordsPerImage the recordsPerImage to set
	 */
	public void setRecordsPerImage(int recordsPerImage) {
		this.recordsPerImage = recordsPerImage;
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

	
}
