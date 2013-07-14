package server.database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import server.ServerException;
import shared.model.User;

/**
 * 
 * @author zaphinath
 *
 */

public class UserDB {
	
	private Database db;

  /**
   * User Database Constructor
   */
  public UserDB(Database db) {
  	this.db = db;
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
  public void add(User user) throws ServerException, SQLException  {
	  PreparedStatement stmt = null;
	  Statement keyStmt = null;
	  ResultSet keyRS = null;
	  try {
	    String sql = "INSERT INTO users (username, password, name_first, name_last, email, indexed) VALUES(?, ?, ?, ?, ?, ?)";
	    stmt = db.getConnection().prepareStatement(sql);
	    stmt.setString(1, user.getUsername());
	    stmt.setString(2, user.getPassword());
	    stmt.setString(3, user.getFirstName());
	    stmt.setString(4, user.getLastName());
	    stmt.setString(5, user.getEmail());
	    stmt.setInt(6, user.getIndexedRecords());
	    if (stmt.executeUpdate() == 1) {
	    } else {
	      System.out.println("Insert user failed");
	    }
	  } catch (SQLException e) {
	    e.printStackTrace();
	  } finally {
	    if (stmt != null) stmt.close();
	    if (keyRS != null) keyRS.close();
	    if (keyStmt != null) keyStmt.close();
	  }
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
