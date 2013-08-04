/**
 * 
 */
package client;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import shared.communication.DownloadBatch_Result;
import shared.model.Field;
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
	private Point framePoint;
	
	private String[][] values;
	private String recordValues;
	private Cell selectedCell;
	private transient List<SessionListener> listeners;
//	private File batchImage;
	
	private int zoomLevel;
	private boolean imageInverted;
	private boolean toggledHighlights;
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String indexedRecords;

	private String urlPrefix;
	
	//private DownloadBatch_Result currentBatch;
	//private int fieldIdSelected;
	
	//BATCH
	private boolean haveBatch;
	
	private int batchId;
	private int projectId;
	private URL imageUrl;
	private int firstYCoord;
	private int recordHeight;
	private int numRecords;
	private int numFields;
	
	private List<Field> fields;
	
	/*
	 * Class Constructor
	 */
	public Session() {
		zoomLevel = -10;
		imageInverted = false;
		toggledHighlights = false;
		
		listeners = new ArrayList<SessionListener>();
		selectedCell = null;
		fields = new ArrayList<Field>();
		
		frameHeight = 800;
		frameWidth = 1200;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		framePoint = new Point(dim.width/2-frameWidth/2, dim.height/2-frameHeight/2);
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
	
	public void clearListeners() {
		listeners.clear();
	}
	
	public void initiliazeListenersList() {
		listeners = new ArrayList<SessionListener>();
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
	 * @return the indexedRecords
	 */
	public String getIndexedRecords() {
		return indexedRecords;
	}

	/**
	 * @param indexedRecords the indexedRecords to set
	 */
	public void setIndexedRecords(String indexedRecords) {
		this.indexedRecords = indexedRecords;
	}

	/**
	 * @return the framePoint
	 */
	public Point getFramePoint() {
		return framePoint;
	}

	/**
	 * @param framePoint the framePoint to set
	 */
	public void setFramePoint(Point framePoint) {
		this.framePoint = framePoint;
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
	 * @return the batchId
	 */
	public int getBatchId() {
		return batchId;
	}

	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the imageUrl
	 */
	public URL getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(URL imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the firstYCoord
	 */
	public int getFirstYCoord() {
		return firstYCoord;
	}

	/**
	 * @param firstYCoord the firstYCoord to set
	 */
	public void setFirstYCoord(int firstYCoord) {
		this.firstYCoord = firstYCoord;
	}

	/**
	 * @return the recordHeight
	 */
	public int getRecordHeight() {
		return recordHeight;
	}

	/**
	 * @param recordHeight the recordHeight to set
	 */
	public void setRecordHeight(int recordHeight) {
		this.recordHeight = recordHeight;
	}

	/**
	 * @return the numRecords
	 */
	public int getNumRecords() {
		return numRecords;
	}

	/**
	 * @param numRecords the numRecords to set
	 */
	public void setNumRecords(int numRecords) {
		this.numRecords = numRecords;
	}

	/**
	 * @return the numFields
	 */
	public int getNumFields() {
		return numFields;
	}

	/**
	 * @param numFields the numFields to set
	 */
	public void setNumFields(int numFields) {
		this.numFields = numFields;
	}

	/**
	 * @return the fields
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/**
	 * @return the currentBatch
	 */
/*	public DownloadBatch_Result getCurrentBatch() {
		return currentBatch;
	}*/
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
		//this.currentBatch = currentBatch;
		
		batchId = currentBatch.getBatchId();
		projectId = currentBatch.getProjectId();
		imageUrl = currentBatch.getImageUrl();
		firstYCoord = currentBatch.getFirstYCoord();
		recordHeight = currentBatch.getRecordHeight();
		numRecords = currentBatch.getNumRecords();
		numFields = currentBatch.getNumFields();
		
		for (int i=0; i < currentBatch.getFields().size(); i++) {
			fields.add(currentBatch.getFields().get(i));
		}
		createValues(currentBatch.getNumRecords(), currentBatch.getNumFields());
		this.setSelectedCell(new Cell(0,0));
		//List<Field> fields;
		setHaveBatch(true);
	}

	/**
	 * @return the recordValues
	 */
	public String getRecordValues() {
		String tmp = "";
		for (int i = 0; i < numRecords; i++) {
			for (int j = 0; j < numFields; j++) {
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
	 * @param width
	 * @param height
	 */
	public void createValues(int width, int height) {
		this.values = new String[width][height];
		for (int i = 0; i < height; i++ ) {
			for (int j = 0; j < width; j++ ) {
				values[j][i] = "";
			}
		}
		
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void setValue(int x, int y, String value) {
		//this.values = new String[x][y];
		this.values[x][y] = value;
		for (SessionListener l : listeners) {
			l.valueChanged(new Cell(x,y), value);
		}
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public String getValue(int x, int y) {
		return this.values[x][y];
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
