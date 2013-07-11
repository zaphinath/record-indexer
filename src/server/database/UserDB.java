package server.database;


import java.util.List;
import java.util.ArrayList;
import shared.model.User;
import server.ServerException;


public class UserDB {

  /**
   * User Database Constructor
   */
  public UserDB() {

  }

  /**
   * This gets all the users from the database and puts them in a list.
   * @return list;
   *
   * @throws ServerException
   */
  public List<User> getAll() throws ServerException {
    List<User> users = new ArrayList<User>(); 
    return users;
  }
  
  /**
   * Adds a user object to the database
   * @param user The User to add to the database
   * 
   * @throws ServerException
   */
  public void add(User user) throws ServerException {
  }

  /**
   * Updates a user in the database from a User object
   * @param user The User to be updated
   *
   * @throws ServerException
   */
  public void update(User user) throws ServerException {

  }
  
  /**
   * Deletes a user from the database
   * @param user The User to be deleted 
   *
   * @throws ServerException
   */
  public void delete(User user) throws ServerException {

  }
}
