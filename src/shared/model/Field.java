package shared.model;

/**
 * 
 * @author zaphinath
 *
 */

public class Field {

  private int id;
  private int projectId;
  private String title;
  private int xcoord;
  private int width;
  private String htmlHelp;
  private String knownData;

  /**
   * Class Constructor 
   */
  public Field() {
  	this.id = -1;
  	this.projectId = -1;
  	this.title = null;
  	this.xcoord = -1;
  	this.width = -1;
  	this.htmlHelp = null;
	  this.knownData = null;
  }
  
  /**
   * Class constructor with params
   * @param id
   * @param projectId
   * @param title
   * @param xcoord
   * @param width
   * @param htmlHelp
   * @param knownData
   */
  public Field(int id, int projectId, String title, int xcoord, int width, String htmlHelp, String knownData) {
  	this.id = id;
  	this.projectId = projectId;
  	this.title = title;
  	this.xcoord = xcoord;
  	this.width = width;
  	this.htmlHelp = htmlHelp;
  	this.knownData = knownData;
  }

  /**
   * Gets the field id
   * @return this.id
   */
	public int getId() {
		return this.id;
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
	 * Sets the field id -- AVOID ever using this
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the field title
	 * @return this.title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the field title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the X coordinate of the field
	 * @return this.xcoord;
	 */
	public int getXcoord() {
		return xcoord;
	}

	/**
	 * Sets the X coordinate of the field
	 * @param xcoord
	 */
	public void setXcoord(int xcoord) {
		this.xcoord = xcoord;
	}

	/**
	 * Gets the field width
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Sets the field width
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets the html help file
	 * @return htmlHelp
	 */
	public String getHtmlHelp() {
		return htmlHelp;
	}

	/**
	 * Sets html help file path
	 * @param htmlHelp
	 */
	public void setHtmlHelp(String htmlHelp) {
		this.htmlHelp = htmlHelp;
	}
	
	/**
	 * Gets the known data for the field
	 * @return knownData
	 */
	public String getKnownData() {
		return knownData;
	}

	/**
	 * Sets the known data for the field
	 * @param knownData
	 */
	public void setKnownData(String knownData) {
		this.knownData = knownData;
	}
  
 
  
  
}
