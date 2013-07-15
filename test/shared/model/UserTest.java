/**
 * 
 */
package shared.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.model.User;

/**
 * @author Derek Carr
 *
 */
public class UserTest {
	
	private int id = 1;
	private String username = "Tristan";
	private String password = "password";
	private String firstName = "Rip Van";
	private String lastName = "Winkle";
	private String email = "test@test.com";
	private int indexedRecords = 11;
	
	private User userDefault;
	private User userParams;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		userDefault = new User();
		userParams = new User(id, username, password, lastName, firstName, email, indexedRecords);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		userDefault = null;
		userParams = null;
	}

	@Test
	public void testDefaultConstructor() {
		assertEquals(-1, userDefault.getID());
		assertEquals(null, userDefault.getUsername());
		assertEquals(null, userDefault.getPassword());
		assertEquals(null, userDefault.getFirstName());
		assertEquals(null, userDefault.getLastName());
		assertEquals(null, userDefault.getEmail());
		assertEquals(-1, userDefault.getIndexedRecords());
	}
	
	@Test
	public void testParamsConstructor() {
		assertEquals(id, userParams.getID());
		assertEquals(username, userParams.getUsername());
		assertEquals(password, userParams.getPassword());
		assertEquals(firstName, userParams.getFirstName());
		assertEquals(lastName, userParams.getLastName());
		assertEquals(email, userParams.getEmail());
		assertEquals(indexedRecords, userParams.getIndexedRecords());
	}
	
	@Test
	public void testSetters() {
		assertEquals(-1, userDefault.getID());
		userDefault.setID(56);
		assertEquals(56, userDefault.getID());
		assertEquals(null, userDefault.getUsername());
		userDefault.setUsername("Spring");
		assertEquals("Spring", userDefault.getUsername());
		assertEquals(null, userDefault.getPassword());
		userDefault.setPassword("test12");
		assertEquals("test12", userDefault.getPassword());
		assertEquals(null, userDefault.getFirstName());
		userDefault.setFirstName("Bob");
		assertEquals("Bob", userDefault.getFirstName());
		assertEquals(null, userDefault.getLastName());
		userDefault.setLastName("Dole");
		assertEquals("Dole", userDefault.getLastName());
		assertEquals(null, userDefault.getEmail());
		userDefault.setEmail("test@test.com");
		assertEquals("test@test.com", userDefault.getEmail());
		assertEquals(-1, userDefault.getIndexedRecords());	
		userDefault.setIndexedRecords(35);
		assertEquals(35, userDefault.getIndexedRecords());
	}

}
