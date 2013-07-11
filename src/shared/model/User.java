package shared.model;

public class User {

  private int id;
  private String username;
  private String password;
  private String nameFirst;
  private String nameLast;
  private String email;
  private int indexedRecords;

/*
 * Class constructor
 */
  User() {

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
   * This gets the username
   * @return this.username
   */
  public String getUsername() {
    return this.username;
  }
  /**
   * Get's the username
   * @return this.nameFirst
   */
  public String getFirstName() {
    return this.nameFirst;
  }
  /**
   * This gets the last name
   * @return this.nameLast
   */
  public String getLastName() {
    return this.nameLast;
  }

}
