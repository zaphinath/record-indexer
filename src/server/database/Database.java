package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.io.File;

import server.ServerException;


/**
 * 
 * @author zaphinath
 *
 */

public class Database {
  private static Logger logger;
	
	private UserDB user;
	private FieldDB field;
	private ProjectDB project;
	
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
    user = new UserDB(this);
    field = new FieldDB(this);
    project = new ProjectDB(this);
  }
 
  /**
   * Loads the SQLLite database driver
   * @throws ServerException
   */
  public static void initialize() throws ServerException {
  	logger.entering("server.database.Database","initialize");
   //TODO Load the SQLLite database driver
    logger.exiting("server.database.Database", "initialize");
  }
  
  /**
   * 
   * @return this.connection
   */
  public Connection getConnection(){
  	return this.connection;
  }
  
  /**
   * Gets the UserDB object.
   * @return this.user;
   */
  public UserDB getUserDB() {
  	return this.user;
  }
  
  /**
   * Gets the FieldDB object
   * @return this.user;
   */
  public FieldDB getFieldDB(){
  	return this.field;
  }
  
  /**
   * Gets this ProjectDB object
   * @return this.project;
   */
  public ProjectDB getProjectDB() {
  	return this.project;
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
  /**
   * @throws ServerException
   */
	public void startTransaction() throws ServerException {
		
		//logger.entering("server.database.Database", "startTransaction");
		
		// TODO: Open a connection to the database and start a transaction
		//logger.fine("TODO: Open a connection to the database and start a transaction");
		
		//logger.exiting("server.database.Database", "startTransaction");
	}
	
	/**
	 * 
	 * @param commit
	 */
	public void endTransaction(boolean commit) {
		
		//logger.entering("server.database.Database", "endTransaction");
		
		// TODO: Commit or rollback the transaction and close the connection
		//logger.fine("TODO: Commit or rollback the transaction and close the connection");
		
		//logger.exiting("server.database.Database", "endTransaction");
	}

} 
