/**
 * 
 */
package client.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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

	public void useDictionaryURL(String urlDict) throws IOException {
		try {
	      root.clearList();
	      this.root = new Words();
	      URL url = new URL(urlDict);
	      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	      /*sc.useDelimiter(", *");
	      while (in.hasNext()) {
	    	  String tmp = sc.next().trim();
	    	  root.add(tmp);
	      }
	      sc.close();*/
	      String inputLine;
	      while ((inputLine = in.readLine()) != null) {
	    	  String[] values = inputLine.split(",");
	    	  for (int i = 0; i < values.length; i++) {
	    		  root.add(values[i]);
	    	  }
	      }
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
