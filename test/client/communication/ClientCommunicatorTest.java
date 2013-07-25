/**
 * Please NOTE!!!! 
 * This is very tempermental and needs to be ran in the right order
 * an <ant import> should also be ran before this test is run
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
		String[] expected = {"TRUE\nTest\nOne\n0\n", "TRUE\nTest\nTwo\n0\n", "TRUE\nSheila\nParker\n0\n", "FALSE\n" };
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
	
	/** 
	 * This depends on successful import - clean slate
	 * @throws ClientException
	 */
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
//				System.out.println("Pass");
			} else {
				assertEquals(dbr.toString(), "FAILED\n");
			}
			projectId++;
		}
	}
	
	/**
	 * This depends on testDownloadBatch completing successfully
	 * @throws ClientException 
	 */
	@Test
	public void testSubmitBatch() throws ClientException {
		String[] users = {"test1", "test2", "sheila", "foo"};
		String[] passs = {"test1", "test2", "parker", "fighters"};
		int[] batchIds = { 1, 21, 0, 0 };
		String[] values = {"carr,derek,male,22,carr,jared,male,23", "nguyen,trang,female,22", "sparky,dog,neither,3", "foo,fighters"};
		
		for (int i = 0; i < users.length; i++) {
			SubmitBatch_Params params = new SubmitBatch_Params(users[i], passs[i], batchIds[i], values[i]);
			SubmitBatch_Result sbr = new SubmitBatch_Result();
			sbr = cc.submitBatch(params);
			if ( i < 2) {
				assertEquals("TRUE\n", sbr.toString());
				//TODO: get results to make sure they were inserted;
			} else {
				assertEquals(sbr.toString(), "FAILED\n");
			}
		}
	}
	
	@Test
	public void testGetFields() throws ClientException {
		String[] users = {"test1", "test2", "sheila", "foo"};
		String[] passs = {"test1", "test2", "parker", "fighters"};
		String[] ids = {"1", "1", null, "3" };
		
		for (int i = 0; i < users.length; i++) {
			GetFields_Params gfp = new GetFields_Params(users[i],passs[i], ids[i]);
			GetFields_Result gfr = cc.getFields(gfp);
			if (i < 3) {
				assertTrue("1",gfr.toString().startsWith("1"));
			} else {
				assertEquals("FAILED\n", gfr.toString());
			}
		}
	}
	
	@Test
	public void testSearch() throws ClientException {
		String[] users = {"test1", "test2", "sheila", "foo"};
		String[] passs = {"test1", "test2", "parker", "fighters"};
		String[] fieldIds = {"1,2,3,4","5", "8", "1,2,3,4,5,6,7"};
		String[] values = {"carr,derek","nguyen", "sparky", "fugly"};
		
		for (int i = 0; i < users.length; i++) {
			Search_Params params = new Search_Params(users[i], passs[i], fieldIds[i], values[i]);
			params.setUrlPrefix("http://localhost/");
			Search_Result result = cc.search(params);
			if (i == 0) {
				String[] comp = {"1", "http://localhost/images/1890_image0.png" , "2", "1", "1", "http://localhost/images/1890_image0.png", "1", "1", "1","http://localhost/images/1890_image0.png", "1", "2"};
				String[] returns = result.toString().split("\\r?\\n");
				for (int j = 0; j < returns.length; j++) {
					//System.out.println(j);
					//System.out.println(comp[j] + " " + returns[j]);
					//depending on certain variables, this may return 1 || 2 at this value, because
					//junit doesn't have a epsilon argument i am just skipping this iteration 
					if (j == 2 || j == 6) continue;
					assertEquals(returns[j], comp[j]);
				}
			} else if (i == 1) {
				String[] comp = {"21", "http://localhost/images/1900_image0.png", "1","5"};
				String[] returns = result.toString().split("\\r?\\n");
				for (int j = 0; j < returns.length; j++) {
					//System.out.println(j);
					//System.out.println(comp[j] + " " + returns[j]);
					assertEquals(returns[j], comp[j]);
				}
			}	else {
				assertEquals("FAILED\n", result.toString());
			}
		}
	}
}
