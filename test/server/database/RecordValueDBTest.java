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
import shared.model.RecordValue;

/**
 * @author Derek Carr
 *
 */
public class RecordValueDBTest {

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
	private RecordValueDB recordValueDb;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		db = new Database("indexer_server_test.db");
		db.startTransaction();
		recordValueDb = db.getRecordValueDB();
		List<RecordValue> list = recordValueDb.getAll();
		
		for (RecordValue rc : list) {
			db.getRecordValueDB().deleteRecordValue(rc);
		}
		db.endTransaction(true);
		
		db = new Database("indexer_server_test.db");
		db.startTransaction();
		recordValueDb = db.getRecordValueDB();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		db.endTransaction(false);
		db = null;
		recordValueDb = null;
	}

	@Test
	public void testAddRecordValue() throws SQLException, ServerException {
		List<RecordValue> list = recordValueDb.getAll();
		assertEquals(0, list.size());
		
		RecordValue rc1 = new RecordValue(-1, 1, 1, "Aunt Grettle",1);
		RecordValue rc2 = new RecordValue(-1, 2, 2, "Uncle Stinky",2);
		
		rc1 = recordValueDb.addRecordValue(rc1);
		rc2 = recordValueDb.addRecordValue(rc2);
		
		list = recordValueDb.getAll();
		assertEquals(2, list.size());

		assertEquals(rc1.getBatchId(), list.get(0).getBatchId());
		assertEquals(rc1.getFieldId(), list.get(0).getFieldId());
		assertEquals(rc1.getValue(), list.get(0).getValue());
		
		assertEquals(rc2.getBatchId(), list.get(1).getBatchId());
		assertEquals(rc2.getFieldId(), list.get(1).getFieldId());
		assertEquals(rc2.getValue(), list.get(1).getValue());
	}
	
	@Test
	public void testUpdateRecordValue() throws SQLException, ServerException {
		List<RecordValue> list = recordValueDb.getAll();
		assertEquals(0, list.size());
		
		RecordValue rc1 = new RecordValue(-1, 1, 1, "Aunt Grettle",1);
		RecordValue rc2 = new RecordValue(-1, 2, 2, "Uncle Stinky",2);
		
		rc1 = recordValueDb.addRecordValue(rc1);
		rc2 = recordValueDb.addRecordValue(rc2);
		
		list = recordValueDb.getAll();
		assertEquals(2, list.size());
		
		rc1.setBatchId(22);
		rc1.setFieldId(33);
		rc1.setValue("SmokingGun");
		
		rc2.setBatchId(8);
		rc2.setFieldId(9);
		rc2.setValue("PinkPanther");
		
		recordValueDb.updateRecordValue(rc1);
		recordValueDb.updateRecordValue(rc2);
		
		list = recordValueDb.getAll();
		assertEquals(2, list.size());
		
		assertEquals(rc1.getBatchId(), list.get(0).getBatchId());
		assertEquals(rc1.getFieldId(), list.get(0).getFieldId());
		assertEquals(rc1.getValue(), list.get(0).getValue());
		
		assertEquals(rc2.getBatchId(), list.get(1).getBatchId());
		assertEquals(rc2.getFieldId(), list.get(1).getFieldId());
		assertEquals(rc2.getValue(), list.get(1).getValue());
	}

	@Test
	public void testDeleteRecordValue() throws SQLException, ServerException {
		List<RecordValue> list = recordValueDb.getAll();
		assertEquals(0, list.size());
		
		RecordValue rc1 = new RecordValue(-1, 1, 1, "Aunt Grettle",1);
		RecordValue rc2 = new RecordValue(-1, 2, 2, "Uncle Stinky",2);
		
		rc1 = recordValueDb.addRecordValue(rc1);
		rc2 = recordValueDb.addRecordValue(rc2);
		
		list = recordValueDb.getAll();
		assertEquals(2, list.size());
		
		for (RecordValue rc : list) {
			db.getRecordValueDB().deleteRecordValue(rc);
		}
		
		list = recordValueDb.getAll();
		assertEquals(0, list.size());		
	}
}
