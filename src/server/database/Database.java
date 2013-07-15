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
	
	private String dbFile = "indexer_server.db";
  private String dbName = "database"+File.separator+dbFile;
  private String connectionURL = "jdbc:sqlite:"+dbName;
  
  private final static String driver = "org.sqlite.JDBC";

  private Connection connection = null;
  /**
   * Class constructor
   */
  public Database() {
    user = new UserDB(this);
    field = new FieldDB(this);
    project = new ProjectDB(this);
  }
 
  /**
   * Loads the SQLLite database driver
   * @throws ServerException
 * @throws ClassNotFoundException 
   */
  public static void initialize() throws ServerException, ClassNotFoundException {
  	//logger.entering("server.database.Database","initialize");
  	Class.forName(driver);
    //logger.exiting("server.database.Database", "initialize");
  }
  
  /**
   * This sets the database file if it needs to be different
   * I.E. Use this when running unit tests to use a clean database 
   * that gets copied by ant
   * @param db
   */
  public void setDbFile(String db) {
  	this.dbFile = db;
  }
  
  /**
   * Gets the database connection
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
   * Starts a database transaction without auto-commit 
   * @throws ServerException
   */
	public void startTransaction() throws ServerException {
		
		//logger.entering("server.database.Database", "startTransaction");
		
		// TODO: Open a connection to the database and start a transaction
		try { 
			connection = DriverManager.getConnection(connectionURL);
			System.out.println(this.connectionURL);
			connection.setAutoCommit(false);
		} catch (SQLException e) { e.printStackTrace(); }
		//logger.fine("TODO: Open a connection to the database and start a transaction");
		
		//logger.exiting("server.database.Database", "startTransaction");
	}
	
	/**
	 * Ends a database transaction
	 * @param commit
	 * @throws SQLException 
	 */
	public void endTransaction(boolean commit) throws SQLException {
		
		//logger.entering("server.database.Database", "endTransaction");
		
		// TODO: Commit or rollback the transaction and close the connection
		try {
			if (commit == true) {
				connection.commit();
			} else {
				connection.rollback();
			}
		} 
		catch (SQLException e) { e.printStackTrace(); }
		finally { connection.close();}
		
		connection = null;
		//logger.fine("TODO: Commit or rollback the transaction and close the connection");
		
		//logger.exiting("server.database.Database", "endTransaction");
	}

} 
