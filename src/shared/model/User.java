package shared.model;

public class User {

  private int id;
  private String username;
  private String password;
  private String nameFirst;
  private String nameLast;
  

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
   * This gets the username
   * @return this.username
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * This sets the first name 
   * @param firstName First Name
   */
  public void setFirstName(String firstName) {
    this.nameFirst = firstName;
  }
  /**
   * @return this.nameFirst
   */
  public String getFirstName() {
    return this.nameFirst;
  }
  /**
   * This sets the last name 
   * @param lastName Last Name
   */
  public void setLastName(String lastName) {
    this.nameLast = lastName;
  }
  /**
   * This gets the last name
   * @return this.nameLast
   */
  public String getLastName() {
    return this.nameLast;
  }

}
