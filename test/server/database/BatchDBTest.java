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
import shared.model.Batch;

/**
 * @author Derek Carr
 *
 */
public class BatchDBTest {

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
	private BatchDB batchDb;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		db = new Database("indexer_server_test.db");
		db.startTransaction();
		batchDb = db.getBatchDB();
		List<Batch> batches = batchDb.getAll();
		
		for (Batch batch : batches) {
			db.getBatchDB().deleteBatch(batch);
		}
		
		db.endTransaction(true);
		
		db = new Database("indexer_server_test.db");
		db.startTransaction();
		batchDb = db.getBatchDB();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		db.endTransaction(false);
		
		db = null;
		batchDb = null;
	}

	@Test
	public void testAddBatch() throws SQLException, ServerException {
		Batch batch1 = new Batch(-1, 2, "SuperStrings.png", 0);
		Batch batch2 = new Batch(-1, 1, "TheIncredibleBulk1880.jpg", 0);
		
		List<Batch> list = batchDb.getAll();
		assertEquals(0, list.size());
		
		batchDb.addBatch(batch1);
		batchDb.addBatch(batch2);
		
		list = batchDb.getAll();
		assertEquals(2, list.size());
		
		assertEquals(batch1.getProjectId(), list.get(0).getProjectId());
		assertEquals(batch1.getFile(), list.get(0).getFile());
		
		assertEquals(batch2.getProjectId(), list.get(1).getProjectId());
		assertEquals(batch2.getFile(), list.get(1).getFile());
	}
	
	@Test
	public void testUpdateBatch() throws SQLException, ServerException {
		Batch batch1 = new Batch(-1, 2, "SuperStrings.png", 1);
		Batch batch2 = new Batch(-1, 1, "TheIncredibleBulk1880.jpg", 2);
		
		batch1 = batchDb.addBatch(batch1);
		batch2 = batchDb.addBatch(batch2);
		
		List<Batch> list = batchDb.getAll();
		assertEquals(2, list.size());
		
		batch1.setProjectId(2012);
		batch1.setFile("BigFoot");
		batch1.setAccessUser(2);
		
		batch2.setProjectId(22);
		batch2.setFile("Sasquatch");
		batch2.setAccessUser(1);
		
		batchDb.updateBatch(batch1);
		batchDb.updateBatch(batch2);
		
		list = batchDb.getAll();
		assertEquals(2, list.size());
		
		assertEquals(batch1.getProjectId(), list.get(0).getProjectId());
		assertEquals(batch1.getFile(), list.get(0).getFile());
		assertEquals(batch1.getAccessUserId(), list.get(0).getAccessUserId());
		
		assertEquals(batch2.getProjectId(), list.get(1).getProjectId());
		assertEquals(batch2.getFile(), list.get(1).getFile());
		assertEquals(batch2.getAccessUserId(), list.get(1).getAccessUserId());
	}
	
	@Test 
	public void testDeleteBatch() throws SQLException, ServerException {
		Batch batch1 = new Batch(-1, 2, "SuperStrings.png", 1);
		Batch batch2 = new Batch(-1, 1, "TheIncredibleBulk1880.jpg", 2);
		
		batch1 = batchDb.addBatch(batch1);
		batch2 = batchDb.addBatch(batch2);
		
		List<Batch> list = batchDb.getAll();
		assertEquals(2, list.size());
		
		for (Batch batch : list) {
			db.getBatchDB().deleteBatch(batch);
		}
		
		list = batchDb.getAll();
		assertEquals(0, list.size());
	}

}
