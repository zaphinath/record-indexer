/**
 * 
 */
package shared.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.model.Project;

/**
 * @author Derek Carr
 *
 */
public class ProjectTest {

	private Project defaultProject;
	private Project paramsProject;
	
	private int id = 23;
	private String title = "1980 Census";
	private int recordsPerImage = 11;
	private int firstYCoord = 65;
	private int recordHeight = 600;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		defaultProject = new Project();
		paramsProject = new Project(id, title, recordsPerImage, firstYCoord, recordHeight);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		defaultProject = null;
		paramsProject = null;
	}

	@Test
	public void testDefaultConstructor() {
		assertEquals(-1, defaultProject.getId());
		assertEquals(null,defaultProject.getTitle());
		assertEquals(-1, defaultProject.getRecordsPerImage());
		assertEquals(-1, defaultProject.getFirstYCoord());
		assertEquals(-1, defaultProject.getRecordHeight());
	}
	
	@Test
	public void testParamsConstructor() {
		assertEquals(id, paramsProject.getId());
		assertEquals(title, paramsProject.getTitle());
		assertEquals(recordsPerImage, paramsProject.getRecordsPerImage());
		assertEquals(firstYCoord, paramsProject.getFirstYCoord());
		assertEquals(recordHeight, paramsProject.getRecordHeight());
	}
	
	@Test
	public void testSetters() {
		assertEquals(-1, defaultProject.getId());
		defaultProject.setId(52);
		assertEquals(52, defaultProject.getId());
		assertEquals(null,defaultProject.getTitle());
		defaultProject.setTitle("MutantTurtles_1999");
		assertEquals("MutantTurtles_1999",defaultProject.getTitle());
		assertEquals(-1, defaultProject.getRecordsPerImage());
		defaultProject.setRecordsPerImage(19920);
		assertEquals(19920, defaultProject.getRecordsPerImage());
		assertEquals(-1, defaultProject.getFirstYCoord());
		defaultProject.setFirstYCoord(55);
		assertEquals(55, defaultProject.getFirstYCoord());
		assertEquals(-1, defaultProject.getRecordHeight());
		defaultProject.setRecordHeight(800);
		assertEquals(800, defaultProject.getRecordHeight());
	}

}
