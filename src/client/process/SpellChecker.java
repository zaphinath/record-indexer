/**
 * 
 */
package client.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Derek Carr
 *
 */
public class SpellChecker {

	@SuppressWarnings("serial")
	public static class NoSimilarWordFoundException extends Exception {
	}
	private Words root;

	public SpellChecker() {
		this.root = new Words();
	}

	public void useDictionary(String dictionaryFileName) throws IOException {
		try {
	      root.clearList();
	      this.root = new Words();
	      Scanner sc = new Scanner(new File(dictionaryFileName));
	      sc.useDelimiter(", *");
	      while (sc.hasNext()) {
	    	  String tmp = sc.next().trim();
	    	  root.add(tmp);
	      }
	      sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("");
		}

	}


	public ArrayList<String> suggestSimilarWords(String inputWord) {
		root.clearList();
		ArrayList<String> tmp;
		if (root.find(inputWord) != null) {
			return null;
		} else {
			tmp = root.filterList(inputWord); 
		}
		return tmp;   
   }
	    
}
