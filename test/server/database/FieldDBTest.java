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
import shared.model.Field;

/**
 * @author Derek Carr
 *
 */
public class FieldDBTest {

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
	private FieldDB fieldDb;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		db = new Database("indexer_server_test.db");
		db.startTransaction();
		fieldDb = db.getFieldDB();
		List<Field> fields = fieldDb.getAll();
		
		for (Field field : fields) {
			db.getFieldDB().delete(field);
		}
		
		db.endTransaction(true);
		
		db = new Database("indexer_server_test.db");
		db.startTransaction();
		fieldDb = db.getFieldDB();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		db.endTransaction(false);
		
		db = null;
		fieldDb = null;
	}

	@Test
	public void testAddField() throws ServerException, SQLException {
		Field field1 = new Field(-1, 1, "GrassIsGreener", 22, 1050, "/var/smells", "/var/knownSmells" );
		Field field2 = new Field(-1, 2, "BrownIsPinkly", 10, 500, "/var/fruits", "/var/oldFruits");
		
		field1 = fieldDb.add(field1);
		field2 = fieldDb.add(field2);
		
		List<Field> fields = fieldDb.getAll();
		assertEquals(2, fields.size());
		
		assertEquals(field1.getId(), fields.get(0).getId());
		assertEquals(field1.getProjectId(), fields.get(0).getProjectId());
		assertEquals(field1.getTitle(), fields.get(0).getTitle());
		assertEquals(field1.getXcoord(), fields.get(0).getXcoord());
		assertEquals(field1.getWidth(), fields.get(0).getWidth());
		assertEquals(field1.getHtmlHelp(), fields.get(0).getHtmlHelp());
		assertEquals(field1.getKnownData(), fields.get(0).getKnownData());
		
		assertEquals(field2.getId(), fields.get(1).getId());
		assertEquals(field2.getProjectId(), fields.get(1).getProjectId());
		assertEquals(field2.getTitle(), fields.get(1).getTitle());
		assertEquals(field2.getXcoord(), fields.get(1).getXcoord());
		assertEquals(field2.getWidth(), fields.get(1).getWidth());
		assertEquals(field2.getHtmlHelp(), fields.get(1).getHtmlHelp());
		assertEquals(field2.getKnownData(), fields.get(1).getKnownData());
	}
	
	@Test
	public void testUpdateField() throws ServerException, SQLException {
		Field field1 = new Field(-1, 1, "GrassIsGreener", 22, 1050, "/var/smells", "/var/knownSmells" );
		Field field2 = new Field(-1, 2, "BrownIsPinkly", 10, 500, "/var/fruits", "/var/oldFruits");
		
		field1 = fieldDb.add(field1);
		field2 = fieldDb.add(field2);
				
		List<Field> fields = fieldDb.getAll();
		assertEquals(2, fields.size());
		
		field1.setProjectId(2);
		field1.setTitle("SkullCandy");
		field1.setXcoord(100);
		field1.setWidth(600);
		field1.setHtmlHelp("index.html");
		field1.setKnownData("HereGoesNothing");
		
		field2.setProjectId(2);
		field2.setTitle("AliensAmongUs");
		field2.setXcoord(0);
		field2.setWidth(1048);
		field2.setHtmlHelp("http://zaphinath.com");
		field2.setKnownData("SideShowBob");
		
		fieldDb.update(field1);
		fieldDb.update(field2);
		
		fields = fieldDb.getAll();
		assertEquals(2, fields.size());
		
		assertEquals(field1.getId(), fields.get(0).getId());
		assertEquals(field1.getProjectId(), fields.get(0).getProjectId());
		assertEquals(field1.getTitle(), fields.get(0).getTitle());
		assertEquals(field1.getXcoord(), fields.get(0).getXcoord());
		assertEquals(field1.getWidth(), fields.get(0).getWidth());
		assertEquals(field1.getHtmlHelp(), fields.get(0).getHtmlHelp());
		assertEquals(field1.getKnownData(), fields.get(0).getKnownData());
		
		assertEquals(field2.getId(), fields.get(1).getId());
		assertEquals(field2.getProjectId(), fields.get(1).getProjectId());
		assertEquals(field2.getTitle(), fields.get(1).getTitle());
		assertEquals(field2.getXcoord(), fields.get(1).getXcoord());
		assertEquals(field2.getWidth(), fields.get(1).getWidth());
		assertEquals(field2.getHtmlHelp(), fields.get(1).getHtmlHelp());
		assertEquals(field2.getKnownData(), fields.get(1).getKnownData());
	}
	
	@Test
	public void testDeleteField() throws ServerException, SQLException {
		Field field1 = new Field(-1, 1, "GrassIsGreener", 22, 1050, "/var/smells", "/var/knownSmells" );
		Field field2 = new Field(-1, 2, "BrownIsPinkly", 10, 500, "/var/fruits", "/var/oldFruits");
		
		field1 = fieldDb.add(field1);
		field2 = fieldDb.add(field2);
				
		List<Field> fields = fieldDb.getAll();
		assertEquals(2, fields.size());
		
		for(Field field : fields) {			
			db.getFieldDB().delete(field);
		}
		
		fields = fieldDb.getAll();
		assertEquals(0, fields.size());
	}

}
