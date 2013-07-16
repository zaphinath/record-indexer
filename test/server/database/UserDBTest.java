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
		db = new Database("indexer_server_test.db");
		db.startTransaction();
		userDb = db.getUserDB();
		List<User> users = userDb.getAll();
		
		for (User user : users) {
			db.getUserDB().delete(user);
		}
		
		db.endTransaction(true);
		
		db = new Database("indexer_server_test.db");
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
	public void testAddUser() throws ServerException, SQLException {
		User user1 = new User(-1, "titanic", "crashing", "John", "Doe", "test@test.com", 3 );
		User user2 = new User(-1, "gitty", "upNow", "Jane", "Doe", "test2@test.com", 0);
		
		user1 = userDb.add(user1);
		user2 = userDb.add(user2);
		
		List<User> all = userDb.getAll();
		assertEquals(2, all.size());
		
		assertEquals("titanic", all.get(0).getUsername());
		assertEquals("crashing", all.get(0).getPassword());
		assertEquals("John", all.get(0).getFirstName());
		assertEquals("Doe", all.get(0).getLastName());
		assertEquals("test@test.com", all.get(0).getEmail());
		assertEquals(3, all.get(0).getIndexedRecords());
		
		assertEquals("gitty", all.get(1).getUsername());
		assertEquals("upNow", all.get(1).getPassword());
		assertEquals("Jane", all.get(1).getFirstName());
		assertEquals("Doe", all.get(1).getLastName());
		assertEquals("test2@test.com", all.get(1).getEmail());
		assertEquals(0, all.get(1).getIndexedRecords());
	}
	
	@Test
	public void testUpdateUser() throws ServerException, SQLException {
		User user = new User(-1, "titanic", "crashing", "John", "Doe", "test@test.com", 3 );
		user = userDb.add(user);
		
		List<User> first = userDb.getAll();
		assertEquals(1, first.size());
		
		user.setUsername("dcarr");
		user.setPassword("test12");
		user.setFirstName("Derek");
		user.setLastName("Carr");
		user.setEmail("dcarr@byu.edu");
		user.setIndexedRecords(55);
				
		userDb.update(user);
		
		List<User> all = userDb.getAll();
		assertEquals(1, all.size());
		
		assertEquals("dcarr", all.get(0).getUsername());
		assertEquals("test12", all.get(0).getPassword());
		assertEquals("Derek", all.get(0).getFirstName());
		assertEquals("Carr", all.get(0).getLastName());
		assertEquals("dcarr@byu.edu", all.get(0).getEmail());
		assertEquals(55, all.get(0).getIndexedRecords());
	}

	@Test
	public void testDeleteUser() throws ServerException, SQLException {
		List<User> all = userDb.getAll();
		assertEquals(0, all.size());
		
		User user = new User(-1, "titanic", "crashing", "John", "Doe", "test@test.com", 3 );
		user = userDb.add(user);
		
		List<User> add = userDb.getAll();
		assertEquals(1, add.size());
		
		userDb.delete(user);
		
		List<User> del = userDb.getAll();
		assertEquals(0, del.size());
	}
	
	/*@Test(expected=SQLException.class)
	public void testInvalidAdd() throws ServerException, SQLException {
		User user = new User(-1, null, null, null, null, null, 0 );
		user = userDb.add(user);
	}*/
}
