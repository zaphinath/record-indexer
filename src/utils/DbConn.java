package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.File;


public class DbConn {
  
  private String dbName = "database"+File.separator+"indexer_server.db";
  private String connectionURL = "jdbc:sqlite:"+dbName;

  private Connection connection = null;
  /**
   * Class constructor
   */
  public DbConn() {
    try {
      connection = DriverManager.getConnection(connectionURL);
    } catch (SQLException e) {
      System.out.println("Could Not Connect");
    }
  }
 
  /**
   * This will insert a user into the database
   * @param user the user's username to login 
   * @param pass the user's password
   * @param firstName the user's first name
   * @param lastName the user's last name
   * @param email the user's email address
   * @param indexed the number of indexed records by this user
   */
  public void insertUser(String user, String pass, String firstName, String lastName, String email, int indexed) 
    throws SQLException {
    PreparedStatement stmt = null;
    Statement keyStmt = null;
    ResultSet keyRS = null;
    try {
      String sql = "INSERT INTO users (username, password, name_first, name_last, email, indexed) VALUES(?, ?, ?, ?, ?, ?)";
      stmt = connection.prepareStatement(sql);
      stmt.setString(1, user);
      stmt.setString(2, pass);
      stmt.setString(3, firstName);
      stmt.setString(4, lastName);
      stmt.setString(5, email);
      stmt.setInt(6, indexed);
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
   * This will close the database connection opened in the constructor
   * This should be called evertyime a DbConn object is initialized 
   */
  public void close() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  } 

} 
