/**
 * 
 */
package client;

import java.io.File;
import java.util.List;

import shared.communication.DownloadBatch_Result;

import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
public class Session {
	
	private String host;
	private int port;
	
	private String[][] values;
	private Cell selectedCell;
	private List<SessionListener> listeners;
	private File batchImage;
	private int zoomLevel;
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	private String urlPrefix;
	
	private boolean haveBatch;
	private DownloadBatch_Result currentBatch;
	
	
	/*
	 * Class Constructor
	 */
	public Session() {
		zoomLevel = 0;
	}
	
	/**
	 * Every class that implements sessionlistener needs to 
	 * add the listener to the list so this session can 
	 * update the requested changes;
	 * @param l
	 */
	public void addListener(SessionListener l) {
		listeners.add(l);
	}
	
	
	/**
	 * @return the urlPrefix
	 */
	public String getUrlPrefix() {
		return urlPrefix;
	}
	/**
	 * @param urlPrefix the urlPrefix to set
	 */
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	public void setUrlPrefix() {
		assert host != null;
		assert port >= 0;
		this.urlPrefix = "http://"+host+":"+port+"/files/";
	}
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the haveBatch
	 */
	public boolean isHaveBatch() {
		return haveBatch;
	}
	/**
	 * @param haveBatch the haveBatch to set
	 */
	private void setHaveBatch(boolean haveBatch) {
		this.haveBatch = haveBatch;
		for(SessionListener l : listeners) {
			l.hasBatchChanged();
		}
	}
	/**
	 * @return the currentBatch
	 */
	public DownloadBatch_Result getCurrentBatch() {
		return currentBatch;
	}
	/**
	 * A batch is downloaded for the user;
	 * Can't download new batch unless this is submitted
	 * Values array is set
	 * boolean hasbatch is changed
	 * alert the listners
	 * @param currentBatch the currentBatch to set
	 */
	public void setCurrentBatch(DownloadBatch_Result currentBatch) {
		this.currentBatch = currentBatch;
		setHaveBatch(true);
	}

	/**
	 * @return the values
	 */
	public String[][] getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(String[][] values) {
		this.values = values;
	}

	/**
	 * @return the selectedCell
	 */
	public Cell getSelectedCell() {
		return selectedCell;
	}

	/**
	 * @param selectedCell the selectedCell to set
	 */
	public void setSelectedCell(Cell selectedCell) {
		this.selectedCell = selectedCell;
		for (SessionListener l : listeners) {
			l.selectedCellChanged(selectedCell);
		}
	}

	/**
	 * @return the zoomLevel
	 */
	public int getZoomLevel() {
		return zoomLevel;
	}

	/**
	 * @param zoomLevel the zoomLevel to set
	 */
	public void setZoomLevel(int zoomLevel) {
		this.zoomLevel = zoomLevel;
		for (SessionListener l : listeners) {
			l.zoomeLevelChanged(zoomLevel);
		}
	}
	
	
	
}
