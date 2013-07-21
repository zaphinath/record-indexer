/**
 * 
 */
package client.communication;

import static org.junit.Assert.*;

import java.net.URL;

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
		String[] expected = {"TRUE\nTest\nOne\n0\n", "TRUE\nTest\nTwo\n0\n", "TRUE\nSheila\nParker\n0\n", "FALSE\n" };
		for (int i = 0; i < users.length; i++) {
			ValidateUser_Params params = new ValidateUser_Params(users[i], passs[i]);
			ValidateUser_Result rs = cc.validateUser(params);
			//System.out.println(expected[i]);
			//System.out.println(rs.toString());
			assertEquals(expected[i], rs.toString());
		}		
	}
	
	@Test
	public void testGetProjects() throws ClientException {
		String[] users = {"test1", "test2", "sheila", "foo"};
		String[] passs = {"test1", "test2", "parker", "fighters"};
		String[] expected = {"1\n1890 Census\n","3\nDraft Records\n", "2\n1900 Census\n"};
		for (int i = 0; i < users.length; i++) {
			GetProjects_Params params = new GetProjects_Params(users[i], passs[i]);
			GetProjects_Result pr = cc.getProjects(params);
			if (i < 3) {
				//System.out.println(pr.toString());
				assertTrue(expected[0],pr.toString().contains(expected[0]));
				assertTrue(expected[1],pr.toString().contains(expected[1]));
				assertTrue(expected[2],pr.toString().contains(expected[2]));
			} else {
				assertEquals(pr.toString(), "FAILED\n");
			}
		}
	}

	@Test
	public void getSampleImage() throws ClientException {
		String[] users = {"test1", "test2", "sheila", "foo"};
		String[] passs = {"test1", "test2", "parker", "fighters"};
		int projectId = 1;
		for (int i = 0; i < users.length; i++) {
			GetSampleImage_Params params = new GetSampleImage_Params(users[i], passs[i], projectId);
			params.setUrlPrefix("http://localhost");
			GetSampleImage_Result ir = cc.getSampleImage(params);

//			System.out.println(projectIds[i]);
			assertEquals(params.getUsername(), users[i]);
			assertEquals(params.getPassword(), passs[i]);
			assertEquals(params.getProjectID(), projectId);
			if (i < 3) {
				assertNotNull("NULL", ir.getImageUrl());
				assertTrue("http://", ir.toString().contains("http://"));
			} else {
				assertEquals(ir.toString(), "FAILED\n");
			}
		}		
	}
	
	@Test
	public void testDownloadBatch() throws ClientException {
		String[] users = {"test1", "test2", "sheila", "foo"};
		String[] passs = {"test1", "test2", "parker", "fighters"};
		String[] projectFirstY = {"199", "204", "195" };
		String[] recordHeights = {"60", "62", "65" };
		int projectId = 1;
		for (int i = 0; i < users.length; i++) {
			DownloadBatch_Params params = new DownloadBatch_Params();
			params.setUsername(users[i]);
			params.setPassword(passs[i]);
			params.setProjectID(projectId);
			System.out.println(params.getProjectID() + " " + params.getUsername() + " " + params.getPassword());
			params.setUrlPrefix("http://localhost");
			DownloadBatch_Result dbr = new DownloadBatch_Result();
			dbr = cc.downloadBatch(params);
			if (i < 2) {
				String[] results = dbr.toString().split("\\r?\\n");
				assertEquals(Integer.toString(projectId), results[1]);
				assertEquals(projectFirstY[i], results[3]);
				assertEquals(recordHeights[i], results[4]);
				// Try to checkout another batch with one assigned
				dbr = cc.downloadBatch(params);
				assertEquals("FAILED\n", dbr.toString());
				System.out.println("Pass");
			} else {
				assertEquals(dbr.toString(), "FAILED\n");
			}
			projectId++;
		}

	}
}
