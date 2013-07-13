package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.File;

import shared.model.User;

/**
 * 
 * @author zaphinath
 *
 */

public class Database {
  
  private String dbName = "database"+File.separator+"indexer_server.db";
  private String connectionURL = "jdbc:sqlite:"+dbName;

  private Connection connection = null;
  /**
   * Class constructor
   */
  public Database() {
    try {
      connection = DriverManager.getConnection(connectionURL);
    } catch (SQLException e) {
      System.out.println("Could Not Connect");
    }
  }
 
  /**
   * This will insert a user into the database
   * @param user The user object to insert into the database
   */
  public void insertUser(User user) 
    throws SQLException {
    PreparedStatement stmt = null;
    Statement keyStmt = null;
    ResultSet keyRS = null;
    try {
      String sql = "INSERT INTO users (username, password, name_first, name_last, email, indexed) VALUES(?, ?, ?, ?, ?, ?)";
      stmt = connection.prepareStatement(sql);
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
