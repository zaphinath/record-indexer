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
	
	/**
	 * Default Class Constructor
	 */
	public Project() {
		this.id = -1;
		this.title = null;
		this.recordsPerImage = -1;
		this.firstYCoord = -1;
		this.recordHeight = -1;
	}
	
	/**
	 * Constructor with params
	 * @param id
	 * @param title
	 * @param recordsPerImage
	 * @param firstYCoord
	 * @param recordHeight
	 */
	public Project(int id, String title, int recordsPerImage, int firstYCoord, int recordHeight) {
		this.id = id;
		this.title = title;
		this.recordsPerImage = recordsPerImage;
		this.firstYCoord = firstYCoord;
		this.recordHeight = recordHeight;
	}

	/**
	 * Gets the project id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the project id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the project title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the project
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the number of records per image for the project
	 * @return the recordsPerImage
	 */
	public int getRecordsPerImage() {
		return recordsPerImage;
	}

	/**
	 * Sets the number of records per image for the project
	 * @param recordsPerImage the recordsPerImage to set
	 */
	public void setRecordsPerImage(int recordsPerImage) {
		this.recordsPerImage = recordsPerImage;
	}

	/**
	 * Gets the first Y coordinate 
	 * @return the firstYCoord
	 */
	public int getFirstYCoord() {
		return firstYCoord;
	}

	/**
	 * Sets the first Y coordinate
	 * @param firstYCoord the firstYCoord to set
	 */
	public void setFirstYCoord(int firstYCoord) {
		this.firstYCoord = firstYCoord;
	}

	/**
	 * Gets the record height;
	 * @return the recordHeight
	 */
	public int getRecordHeight() {
		return recordHeight;
	}

	/**
	 * Sets the record height;
	 * @param recordHeight the recordHeight to set
	 */
	public void setRecordHeight(int recordHeight) {
		this.recordHeight = recordHeight;
	}

	
}
