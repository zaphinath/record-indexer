/**
 * 
 */
package client.communication;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import client.ClientException;

import shared.communication.*;

/**
 * @author Derek Carr
 *
 */
public class ClientCommunicatorTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private ClientCommunicator cc;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cc = new ClientCommunicator();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		cc = null;
	}

	@Test
	public void testValidateUser() throws ClientException {
		String[] users = {"test1", "test2", "sheila", "foo"};
		String[] passs = {"test1", "test2", "parker", "fighters"};
		String[] expected = {"TRUE\nTest\nOne\n\0n", "TRUE\nTest\nTwo\0\n", "TRUE\nSheila\nParker\n0\n", "FAILED\n" };
		for (int i = 0; i < users.length; i++) {
			ValidateUser_Params params = new ValidateUser_Params(users[i], passs[i]);
			ValidateUser_Result rs = cc.validateUser(params);
			
			assertEquals(expected[i], rs.toString());
		}		
	}
	
	@Test
	public void testGetProjects() throws ClientException {
		String[] users = {"test1", "test2", "sheila", "foo"};
		String[] passs = {"test1", "test2", "parker", "fighters"};
		String[] expected = {"1\n1890 Census\n","\3nDraft Records\n", "2\n1900 Census\n"};
		for (int i = 0; i < users.length; i++) {
			GetProjects_Params params = new GetProjects_Params(users[i], passs[i]);
			GetProjects_Result pr = cc.getProjects(params);
			if (i < 3) {
				assertTrue(pr.toString().contains(expected[1]));
				assertTrue(pr.toString().contains(expected[2]));
				assertTrue(pr.toString().contains(expected[3]));
			} else {
				assertEquals(pr.toString(), "FAILED\n");
			}
		}
	}

	@Test
	public void getSampleImage() {
		
	}
}
