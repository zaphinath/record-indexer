package shared.model;

/**
 * 
 * @author zaphinath
 *
 */

public class Field {

  private int id;
  private String title;
  private int xcoord;
  private int width;
  private String htmlHelp;
  private String knownData;

  /**
   * Class Constructor 
   */
  public Field() {
	  
  }

  /**
   * Gets the field id
   * @return this.id
   */
	public int getId() {
		return this.id;
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
