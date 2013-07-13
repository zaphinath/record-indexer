package shared.model;

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
   * 
   * @return this.id
   */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return this.title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return
	 */
	public int getXcoord() {
		return xcoord;
	}

	/**
	 * 
	 * @param xcoord
	 */
	public void setXcoord(int xcoord) {
		this.xcoord = xcoord;
	}

	/**
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 
	 * @return
	 */
	public String getHtmlHelp() {
		return htmlHelp;
	}

	/**
	 * 
	 * @param htmlHelp
	 */
	public void setHtmlHelp(String htmlHelp) {
		this.htmlHelp = htmlHelp;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getKnownData() {
		return knownData;
	}

	/**
	 * 
	 * @param knownData
	 */
	public void setKnownData(String knownData) {
		this.knownData = knownData;
	}
  
 
  
  
}
