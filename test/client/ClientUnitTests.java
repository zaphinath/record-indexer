package client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;

import client.process.SpellChecker;
import static org.junit.Assert.*;

public class ClientUnitTests {
	private File dict;
	private SpellChecker spCheck;
	
	@Before
	public void setup() {
		spCheck = new SpellChecker();
		try {
			spCheck.useDictionary("data/1890_last_names.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void teardown() {
		dict = null;
		spCheck = null;
	}
	
	@Test
	public void testSpacesInWord() {
		ArrayList<String> result = spCheck.suggestSimilarWords("uni tes");
		assertEquals(1, result.size());
	}
	
	@Test 
	public void testHaveWord() {
		ArrayList<String> result = spCheck.suggestSimilarWords("travis");
		assertNull(result);
		result = spCheck.suggestSimilarWords("Travis");
		assertNull(result);
		result = spCheck.suggestSimilarWords("TRAVIS");
		assertNull(result);
		result = spCheck.suggestSimilarWords("branch");
		assertNull(result);
	}
	
	@Test
	public void testEditDistanceOne() {
		ArrayList<String> result = spCheck.suggestSimilarWords("travsi");	
		assertEquals(1,result.size());
		result = spCheck.suggestSimilarWords("ravis");	
		assertEquals(1,result.size());
		result = spCheck.suggestSimilarWords("rtravis");
		assertEquals(1,result.size());
		result = spCheck.suggestSimilarWords("traves");	
		assertEquals(1,result.size());
	}
	
	@Test
	public void testEditDistanceTwo() {
		ArrayList<String> result = spCheck.suggestSimilarWords("travsit");	
		assertEquals(1,result.size());
		result = spCheck.suggestSimilarWords("avis");	
		assertEquals(3,result.size());
		result = spCheck.suggestSimilarWords("rrtravis");
		assertEquals(1,result.size());
		result = spCheck.suggestSimilarWords("treves");	
		assertEquals(1,result.size());
	}
	
	@Test
	public void testNoWord() {
		ArrayList<String> result = spCheck.suggestSimilarWords("facebook");
		//assertEquals(0, result.size());
		assertNull(result);
	}

	public static void main(String[] args) {

		String[] testClasses = new String[] {
				"client.ClientUnitTests"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
}

