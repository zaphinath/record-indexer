/**
 * 
 */
package server.database;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.ServerException;
import shared.model.User;

/**
 * @author Derek Carr
 *
 */
public class UserDBTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Database.initialize();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		return;
	}

	private Database db;
	private UserDB  userDb;
	
	/**
	 * Copy empty database to test database
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		db = new Database();
		db.startTransaction();
		userDb = db.getUserDB();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		db.endTransaction(false);
		
		db = null;
		userDb = null;
	}

	@Test
	public void testAdd() throws ServerException, SQLException {
		User user1 = new User(-1, "titanic", "crashing", "John", "Doe", "test@test.com", 3 );
		User user2 = new User(-1, "gitty", "upNow", "Jane", "Doe", "test2@test.com", 0);
		
		user1 = userDb.add(user1);
		user2 = userDb.add(user2);
		
		List<User> all = userDb.getAll();
		assertEquals(2, all.size());
		
	}

}
