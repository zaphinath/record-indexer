package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;

import shared.model.User;
import shared.model.Field;
import shared.model.Project;
/**
 * 
 * @author zaphinath
 *
 */

public class Database {
  
	private User user;
	private Field field;
	private Project project;
	
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
 
  public Connection getConnection(){
  	return this.connection;
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
