/**
 * 
 */
package client;

import java.io.File;
import java.util.List;

import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
public class Session {
	
	interface SessionListener {
		
	}
	
	private String[][] values;
	private Cell selectedCell;
	private List<SessionListener> listeners;
	private File batchImage;
	private int zoomLevel;
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
}
