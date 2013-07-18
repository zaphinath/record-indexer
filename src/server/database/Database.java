package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;

import server.ServerException;


/**
 * 
 * @author zaphinath
 *
 */

public class Database {
  //private static Logger logger;
	
	private UserDB user;
	private FieldDB field;
	private ProjectDB project;
	private BatchDB batch;
	private RecordValueDB recordValue;
	
	private static String dbFile = "indexer_server.db";
  private static String dbName = "database"+File.separator+dbFile;
  private static String connectionURL = "jdbc:sqlite:"+dbName;
  
  private final static String driver = "org.sqlite.JDBC";

  private Connection connection = null;
  /**
   * Class constructor
   */
  public Database() {
    user = new UserDB(this);
    field = new FieldDB(this);
    project = new ProjectDB(this);
    batch = new BatchDB(this);
    recordValue = new RecordValueDB(this);
  }
  
  /**
   * Class Constructor with param
   * @param database the database file you wish to use instead of the default
   */
  public Database(String database) {
  	dbFile = database;
  	dbName = "database"+File.separator+dbFile;
  	connectionURL = "jdbc:sqlite:"+dbName;
  	user = new UserDB(this);
    field = new FieldDB(this);
    project = new ProjectDB(this);
    batch = new BatchDB(this);
    recordValue = new RecordValueDB(this);
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
   * Gets this BatchDB object
   * @return Batch
   */
  public BatchDB getBatchDB() {
  	return this.batch;
  }
  
  public RecordValueDB getRecordValueDB(){
  	return this.recordValue;
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
