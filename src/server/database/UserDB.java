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
   * @throws SQLException 
   */
  public List<User> getAll() throws ServerException, SQLException {
    List<User> users = new ArrayList<User>();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
    	String sql = "SELECT * FROM users";
    	stmt = db.getConnection().prepareStatement(sql);
    	
    	rs = stmt.executeQuery();
    	while (rs.next()) {
    		int id = rs.getInt(1);
    		String username = rs.getString(2);
    		String password = rs.getString(3);
    		String firstName = rs.getString(4);
    		String lastName = rs.getString(5);
    		String email = rs.getString(6);
    		int indexedRecords = rs.getInt(7);
    		User user = new User(id, username, password, firstName, lastName, email, indexedRecords);
    		users.add(user);
    	}
    } catch (SQLException e) {
    	
    } finally {
    	if (rs != null) rs.close();
    	if (stmt != null) stmt.close();
    }
    return users;
  }
  
  /**
   * Adds a user object to the database
   * @param user The User to add to the database
   * 
   * @throws ServerException
   */
  public User add(User user) throws ServerException, SQLException  {
	  PreparedStatement stmt = null;
	  Statement keyStmt = null;
	  ResultSet keyRS = null;
	  User returnUser = null;
	  try {
	    String sql = "INSERT INTO users (username, password, name_first, name_last, email, indexed_records) VALUES(?, ?, ?, ?, ?, ?)";
	    stmt = db.getConnection().prepareStatement(sql);
	    stmt.setString(1, user.getUsername());
	    stmt.setString(2, user.getPassword());
	    stmt.setString(3, user.getFirstName());
	    stmt.setString(4, user.getLastName());
	    stmt.setString(5, user.getEmail());
	    stmt.setInt(6, user.getIndexedRecords());
	    if (stmt.executeUpdate() == 1) {
	    	System.out.println("INSERT HERE:");
	    	keyStmt = db.getConnection().createStatement();
	    	keyRS = keyStmt.executeQuery("SELECT last_insert_rowid()");
	    	keyRS.next();
	    	int userId = keyRS.getInt(1);
	    	returnUser = new User(userId, user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getIndexedRecords());
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
	  return returnUser;
  }

  /**
   * Updates a user in the database from a User object
   * @param user The User to be updated
   *
   * @throws ServerException
 * @throws SQLException 
   */
  public void update(User user) throws ServerException, SQLException {
	  PreparedStatement stmt = null;
	  Statement keyStmt = null;
	  ResultSet keyRS = null;
	  try {
		  String sql = "UPDATE users set username = ?, password = ?, name_first = ?, name_last = ?, email = ?, indexed_records = ? where id = ?";
		  stmt = db.getConnection().prepareStatement(sql);
		  stmt.setString(1, user.getUsername());
		  stmt.setString(2, user.getPassword());
		  stmt.setString(3, user.getFirstName());
		  stmt.setString(4, user.getLastName());
		  stmt.setString(5, user.getEmail());
		  stmt.setInt(6, user.getIndexedRecords());
		  stmt.setInt(7, user.getID());
		  if (stmt.executeUpdate(sql) == 1) {
			  System.out.println("SUCCESS");
		  } else {
			  System.out.println("UPDATE failed");
		  }
	  } catch (SQLException e) { e.printStackTrace(); }
	  finally {
		  if (stmt != null) stmt.close();
		  if (keyRS != null) keyRS.close();
		  if (keyStmt != null) keyStmt.close();
	  }
  }
  
  /**
   * Deletes a user from the database
   * @param user The User to be deleted 
   *
   * @throws ServerException
   * @throws SQLException 
   */
  public void delete(User user) throws ServerException, SQLException {
  		PreparedStatement stmt = null;
  		ResultSet rs = null;
  		try {
  			String sql = "DELETE FROM users WHERE id = ?";
  			stmt = db.getConnection().prepareStatement(sql);
  			stmt.setInt(1, user.getID());
  			
 			if (stmt.executeUpdate() == 1) {
 				//Success
 			} else {
 				//Fail
 			}
 		} catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			if (stmt != null) stmt.close();
		  if (rs != null) rs.close();
 		}
  }
}
