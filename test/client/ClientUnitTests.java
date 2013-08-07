package client;

import java.io.File;

import org.junit.*;

import client.process.SpellChecker;
import static org.junit.Assert.*;

public class ClientUnitTests {
	private File dict;
	private SpellChecker spCheck;
	
	@Before
	public void setup() {
		dict = new File("data/1890_last_names.txt");
		spCheck = new SpellChecker();
	}
	
	@After
	public void teardown() {
		dict = null;
		spCheck = null;
	}
	
	@Test
	public void testEditDistanceOne() {
		

	}
	
	@Test
	public void testEditDistanceTwo() {
		
	}
	
	@Test
	public void testEditException() {
		
	}

	public static void main(String[] args) {

		String[] testClasses = new String[] {
				"client.ClientUnitTests"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
}

