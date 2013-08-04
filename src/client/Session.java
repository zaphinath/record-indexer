/**
 * 
 */
package client;

import java.io.File;
import java.util.ArrayList;
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
	private int frameWidth;
	private int frameHeight;
	
	private String[][] values;
	private String recordValues;
	private Cell selectedCell;
	private List<SessionListener> listeners;
	private File batchImage;
	
	private int zoomLevel;
	private boolean imageInverted;
	private boolean toggledHighlights;
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	private String urlPrefix;
	
	private boolean haveBatch;
	private DownloadBatch_Result currentBatch;
	private int fieldIdSelected;
	
	//BATCH
	
	
	/*
	 * Class Constructor
	 */
	public Session() {
		zoomLevel = -10;
		imageInverted = false;
		toggledHighlights = false;
		
		listeners = new ArrayList<SessionListener>();
		selectedCell = null;
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
	public void setHaveBatch(boolean haveBatch) {
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
	 * boolean has batch is changed
	 * alert the listeners
	 * @param currentBatch the currentBatch to set
	 */
	public void setCurrentBatch(DownloadBatch_Result currentBatch) {
		assert currentBatch != null;
		//assert !currentBatch.toString().toLowerCase().contains("fail");
		this.currentBatch = currentBatch;
		setValues(currentBatch.getNumRecords(), currentBatch.getNumFields());
		setHaveBatch(true);
	}

	/**
	 * @return the recordValues
	 */
	public String getRecordValues() {
		String tmp = "";
		for (int i = 0; i < currentBatch.getNumRecords(); i++) {
			for (int j = 0; j < currentBatch.getNumFields(); j++) {
				tmp = tmp + "," + values[i][j];
			}
		}
		return recordValues;
	}

	/**
	 * @param recordValues the recordValues to set
	 */
	public void setRecordValues(String recordValues) {
		this.recordValues = recordValues;
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
	 * 
	 * @param x
	 * @param y
	 */
	public void setValues(int x, int y) {
		this.values = new String[x][y];
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

	/**
	 * @return the imageInverted
	 */
	public boolean isImageInverted() {
		return imageInverted;
	}

	/**
	 * @param imageInverted the imageInverted to set
	 */
	public void setImageInverted(boolean imageInverted) {
		this.imageInverted = imageInverted;
		for (SessionListener l : listeners) {
			l.imageInversionChanged(imageInverted);
		}
	}

	/**
	 * @return the toggledHighlights
	 */
	public boolean isToggledHighlights() {
		return toggledHighlights;
	}

	/**
	 * @param toggledHighlights the toggledHighlights to set
	 */
	public void setToggledHighlights(boolean toggledHighlights) {
		this.toggledHighlights = toggledHighlights;
		for (SessionListener l : listeners) {
			l.toggleHighlightsChanged(toggledHighlights);
		}
	}

	/**
	 * @return the frameWidth
	 */
	public int getFrameWidth() {
		return frameWidth;
	}

	/**
	 * @param frameWidth the frameWidth to set
	 */
	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	/**
	 * @return the frameHeight
	 */
	public int getFrameHeight() {
		return frameHeight;
	}

	/**
	 * @param frameHeight the frameHeight to set
	 */
	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}
	
	
	
}
