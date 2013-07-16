/**
 * 
 */
package shared.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Derek Carr
 *
 */
public class FieldTest {

	private Field defaultField;
	private Field paramsField;
	
	private int id = 44;
	private int projectId = 2;
  private String title = "SpongeBob";
  private int xcoord = 40;
  private int width = 500;
  private String htmlHelp = "/help/thisBling.html";
  private String knownData = "/help/noMore";
  

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		defaultField = new Field();
		paramsField = new Field(id, projectId, title, xcoord, width, htmlHelp, knownData);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		defaultField = null;
		paramsField = null;
	}

	@Test
	public void testDefaultConstructor() {
		assertEquals(-1, defaultField.getId());
		assertEquals(-1, defaultField.getProjectId());
		assertEquals(null, defaultField.getTitle());
		assertEquals(-1, defaultField.getXcoord());
		assertEquals(-1, defaultField.getWidth());
		assertEquals(null, defaultField.getHtmlHelp());
		assertEquals(null, defaultField.getKnownData());
	}
	
	@Test
	public void testParamsConstructor() {
		assertEquals(id, paramsField.getId());
		assertEquals(projectId, paramsField.getProjectId());
		assertEquals(title, paramsField.getTitle());
		assertEquals(xcoord, paramsField.getXcoord());
		assertEquals(width, paramsField.getWidth());
		assertEquals(htmlHelp, paramsField.getHtmlHelp());
		assertEquals(knownData, paramsField.getKnownData());
	}
	
	@Test
	public void testSetters() {
		assertEquals(-1, defaultField.getId());
		defaultField.setId(106);
		assertEquals(106, defaultField.getId());
		assertEquals(-1, defaultField.getProjectId());
		defaultField.setProjectId(62);
		assertEquals(62, defaultField.getProjectId());
		assertEquals(null, defaultField.getTitle());
		defaultField.setTitle("GodZilla");
		assertEquals("GodZilla", defaultField.getTitle());
		assertEquals(-1, defaultField.getXcoord());
		defaultField.setXcoord(20);
		assertEquals(20, defaultField.getXcoord());
		assertEquals(-1, defaultField.getWidth());
		defaultField.setWidth(1050);
		assertEquals(1050, defaultField.getWidth());
		assertEquals(null, defaultField.getHtmlHelp());
		defaultField.setHtmlHelp("THISisTHEplace");
		assertEquals("THISisTHEplace", defaultField.getHtmlHelp());
		assertEquals(null, defaultField.getKnownData());
		defaultField.setKnownData("ToBorNot2Be");
		assertEquals("ToBorNot2Be", defaultField.getKnownData());
	}

}
