/**
 * 
 */
package client.component;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import client.Session;
import client.SessionListener;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class ListModel extends AbstractListModel implements SessionListener {
	private Session session;
	private ArrayList<Integer> numRecords;
	
	/**
	 * @param session 
	 * 
	 */
	public ListModel(Session session) {
		this.session = session;
		session.addListener(this);
		
		numRecords = new ArrayList<Integer>();
		if (session.isHaveBatch()) {
			for (int i = 0; i < session.getNumRecords(); i++) {
				numRecords.add(i+1);
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return numRecords.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public Object getElementAt(int index) {
		return numRecords.get(index);
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		if (session.isHaveBatch()) {
			for (int i = 0; i < session.getNumRecords(); i++) {
				numRecords.add(i+1);
				this.fireIntervalAdded(numRecords, 0, numRecords.size());
			}
		} else {
			this.fireIntervalRemoved(numRecords, 0, numRecords.size());
			numRecords.clear();
		}
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#valueChanged(client.model.Cell, java.lang.String)
	 */
	@Override
	public void valueChanged(Cell cell, String newValue) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see client.SessionListener#selectedCellChanged(client.model.Cell)
	 */
	@Override
	public void selectedCellChanged(Cell newSelectedCell) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see client.SessionListener#scaleChanged(double)
	 */
	@Override
	public void scaleChanged(double scale) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see client.SessionListener#toggleHighlightsChanged(boolean)
	 */
	@Override
	public void toggleHighlightsChanged(boolean toggle) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see client.SessionListener#imageInversionChanged(boolean)
	 */
	@Override
	public void imageInversionChanged(boolean inversion) {
		// TODO Auto-generated method stub

	}

}
