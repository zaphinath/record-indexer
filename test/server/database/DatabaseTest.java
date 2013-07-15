/**
 * 
 */
package server.database;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.database.Database;
/**
 * @author Derek Carr
 *
 */
public class DatabaseTest {
	
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		// Load database driver		
		Database.initialize();
	}
	
	@Before
	public void setUp() throws Exception {
		Database db = new Database();
		db.startTransaction();
		
		db.endTransaction(true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void setUP() throws Exception {

		
	}

}
