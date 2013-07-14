package server;

import org.junit.* ;
import static org.junit.Assert.* ;

public class ServerUnitTests {
	
	@Before
	public void setup() {
	}
	
	@After
	public void teardown() {
	}
	

  
  @Test 
  public void testAddUser() {

  }

	@Test
	public void test_1() {
		//assertEquals("OKi", "OK");
		assertTrue(true);
		assertFalse(false);
	}
  
  @Test
  public void test() {
    System.out.println("HITEST");
  }

	public static void main(String[] args) {
		
		String[] testClasses = new String[] {
				"server.ServerUnitTests"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
	
}

