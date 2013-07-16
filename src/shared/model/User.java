package shared.model;

/**
 * 
 * @author zaphinath
 *
 */

public class User {

  private int id;
  private String username;
  private String password;
  private String nameFirst;
  private String nameLast;
  private String email;
  private String openImage;
  private int indexedRecords;

/*
 * Class constructor 
 */
  public User() {
	  this.id = -1;
	  this.username = null;
	  this.password = null;
	  this.nameFirst = null;
	  this.nameLast = null;
	  this.email = null;
	  this.openImage = null;
	  this.indexedRecords = -1;
  }
  
  /**
   * Class Constructor with params
   * @param id
   * @param username
   * @param password
   * @param firstName
   * @param lastName
   * @param email
   * @param indexedRecords
   */
  public User(int id, String username, String password, String firstName, String lastName, String email, int indexedRecords) {
	  this.id = id;
	  this.username = username;
	  this.password = password;
	  this.nameLast = lastName;
	  this.nameFirst = firstName;
	  this.email = email;
	  this.indexedRecords = indexedRecords;
  }

  /**
   * This sets the id
   * @param id The ID
   */
  public void setID(int id) {
	  this.id = id;
  }
  
  /**
   * This sets the username
   * @param user username
   */
  public void setUsername(String user) {
    this.username = user;
  }
  
  /** 
   * This sets the password
   * @param pass The password
   */
  public void setPassword(String pass) {
    this.password = pass;
  }
  
  /**
   * This sets the first name 
   * @param firstName First Name
   */
  public void setFirstName(String firstName) {
    this.nameFirst = firstName;
  }
  
  /**
   * This sets the last name 
   * @param lastName Last Name
   */
  public void setLastName(String lastName) {
    this.nameLast = lastName;
  }

  /**
   * Set the user's email address
   * @param email The user's email address
   */
  public void setEmail(String email) {
    this.email = email;
  }
  
  /**
   * Sets the number of Indexed Records
   * @param num Number of indexed records user has done
   */
  public void setIndexedRecords(int num) {
    this.indexedRecords = num;
  }

  /**
   * Returns the id for this user.
   * @return this.id;
   */
  public int getID() {
  	return this.id;
  }
  /**
   * This gets the username
   * @return this.username
   */
  public String getUsername() {
    return this.username;
  }

  /** 
   * This gets the User's password 
   * @return this.password The user's password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Get's the username
   * @return this.nameFirst The user's first name
   */
  public String getFirstName() {
    return this.nameFirst;
  }
  
  /**
   * This gets the last name
   * @return this.nameLast The user's last name
   */
  public String getLastName() {
    return this.nameLast;
  }

  /**
   * This gets the user's email
   * @return this.email The user's email address
   */
  public String getEmail() {
    return this.email;
  }
  
  /**
   * This returns the number of indexed records
   * @return this.indexedRecords The number of records the user has indexed
   */
  public int getIndexedRecords() {
    return this.indexedRecords;
  }

	/**
	 * The user can only have one open image at a time
	 * @return the openImage
	 */
	public String getOpenImage() {
		return openImage;
	}

	/**
	 * When an user opens an image set the file url here
	 * @param openImage the openImage to set
	 */
	public void setOpenImage(String openImage) {
		this.openImage = openImage;
	}

}
