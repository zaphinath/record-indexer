/**
 * 
 */
package server.database;

import static org.junit.Assert.*;
import shared.model.Project;
import server.ServerException;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author zaphinath
 *
 */
public class ProjectDBTest {

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
	private ProjectDB  projectDb;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		db = new Database("indexer_server_test.db");
		db.startTransaction();
		projectDb = db.getProjectDB();
		List<Project> projects = projectDb.getAll();
		
		for (Project project : projects) {
			db.getProjectDB().delete(project);
		}
		
		db.endTransaction(true);
		
		db = new Database("indexer_server_test.db");
		db.startTransaction();
		projectDb = db.getProjectDB();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		db.endTransaction(false);
		
		db = null;
		projectDb = null;
	}

	@Test
	public void testAddProject() throws ServerException, SQLException {
		Project project1 = new Project(-1, "title", 20, 0, 800);
		Project project2 = new Project(-1, "facePlant", 13, 10, 600);
		
		project1 = projectDb.add(project1);
		project2 = projectDb.add(project2);
		
		List<Project> all = projectDb.getAll();
		assertEquals(2,all.size());
		
		assertEquals(project1.getId(), all.get(0).getId());
		assertEquals(project1.getTitle(), all.get(0).getTitle());
		assertEquals(project1.getRecordsPerImage(), all.get(0).getRecordsPerImage());
		assertEquals(project1.getFirstYCoord(), all.get(0).getFirstYCoord());
		assertEquals(project1.getRecordHeight(), all.get(0).getRecordHeight());
		
		assertEquals(project2.getId(), all.get(1).getId());
		assertEquals(project2.getTitle(), all.get(1).getTitle());
		assertEquals(project2.getRecordsPerImage(), all.get(1).getRecordsPerImage());
		assertEquals(project2.getFirstYCoord(), all.get(1).getFirstYCoord());
		assertEquals(project2.getRecordHeight(), all.get(1).getRecordHeight());
	}

	@Test
	public void testUpdateProject() throws ServerException, SQLException {
		Project project1 = new Project(-1, "title", 20, 0, 800);
		Project project2 = new Project(-1, "facePlant", 13, 10, 600);
		
		project1 = projectDb.add(project1);
		project2 = projectDb.add(project2);
		
		List<Project> all = projectDb.getAll();
		assertEquals(2,all.size());
		
		project1.setTitle("Holy");
		project1.setRecordsPerImage(2);
		project1.setFirstYCoord(10);
		project1.setRecordHeight(1600);
		
		project2.setTitle("Bible");
		project2.setRecordsPerImage(0);
		project2.setFirstYCoord(100);
		project2.setRecordHeight(800);
		
		projectDb.update(project1);
		projectDb.update(project2);
		
		all = projectDb.getAll();
		
		assertEquals(project1.getId(), all.get(0).getId());
		assertEquals(project1.getTitle(), all.get(0).getTitle());
		assertEquals(project1.getRecordsPerImage(), all.get(0).getRecordsPerImage());
		assertEquals(project1.getFirstYCoord(), all.get(0).getFirstYCoord());
		assertEquals(project1.getRecordHeight(), all.get(0).getRecordHeight());
		
		assertEquals(project2.getId(), all.get(1).getId());
		assertEquals(project2.getTitle(), all.get(1).getTitle());
		assertEquals(project2.getRecordsPerImage(), all.get(1).getRecordsPerImage());
		assertEquals(project2.getFirstYCoord(), all.get(1).getFirstYCoord());
		assertEquals(project2.getRecordHeight(), all.get(1).getRecordHeight());
		
		all = projectDb.getAll();
		assertEquals(2,all.size());
	}
	
	@Test
	public void testDeleteProject() throws ServerException, SQLException {
		Project project1 = new Project(-1, "title", 20, 0, 800);
		Project project2 = new Project(-1, "facePlant", 13, 10, 600);
		
		project1 = projectDb.add(project1);
		project2 = projectDb.add(project2);
		
		List<Project> all = projectDb.getAll();
		assertEquals(2,all.size());
		
		for (Project project : all) {
			db.getProjectDB().delete(project);
		}
		
		all = projectDb.getAll();
		assertEquals(0,all.size());
	}
}
