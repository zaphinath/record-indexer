/**
 * 
 */
package client;

import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
public interface SessionListener {
	
	public void hasBatchChanged();
	
	public void valueChanged(Cell cell, String newValue);
	
	public void selectedCellChanged(Cell newSelectedCell);
	
	public void zoomeLevelChanged(int zoom);
	
	public void toggleHighlightsChanged(boolean toggle);
	
	public void imageInversionChanged(boolean inversion);

}
