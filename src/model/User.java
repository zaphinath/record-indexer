package model;

public class User {

  private String nameFirst;


/*
 * Class constructor
 */
  User() {

  }
  /*
   * @param name First Name
   */
  public void setFirstName(String name) {
    nameFirst = name;
  }
  /*
   * @return this.nameFirst
   */
  public String getFirstName() {
    return this.nameFirst;
  }


}
