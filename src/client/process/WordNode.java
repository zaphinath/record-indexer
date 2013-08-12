/**
 * 
 */
package client.process;

import client.process.Trie.Node;

/**
 * @author zaphinath
 *
 */
public class WordNode implements Trie.Node {

	  public int count = 0;
	  public WordNode next;
	  public WordNode prev;
	  public WordNode[] subNodes;
	  public char letter;
	  
	  
	  /**
	   * Class Constructor
	   */
	  public WordNode(){
	    prev = null;
	    next = null;
	    subNodes = new WordNode[27];
	  }

	  /**
	   * Returns the frequency count for the word represented by the node
	   * @return The frequency count for the word represented by the node
	   */
	  public int getValue() { 
		  return this.count;
	  }
}
