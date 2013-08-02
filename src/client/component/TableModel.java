/**
 * 
 */
package client.component;

import javax.swing.table.AbstractTableModel;

import client.Session;
import client.SessionListener;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel implements SessionListener {
	private Session session;
	
	/**
	 * @param session
	 */
	public TableModel(Session session) {
		super();
		this.session = session;
		if (session.isHaveBatch()) {
			initialize();
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return session.getCurrentBatch().getNumRecords();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return session.getCurrentBatch().getNumFields() ;
	}
	
	@Override
	public String getColumnName(int column) {

		String result = null;

		if (column >= 0 && column < getColumnCount()) {
			result = session.getCurrentBatch().getFields().get(column).getTitle();
		} else {
			throw new IndexOutOfBoundsException();
		}
		return result;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return (row == 0 || column == 0) ? false : true;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		if (session.isHaveBatch()) {
			initialize();
		} else {
			destroy();
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

	/**
	 * 
	 */
	private void initialize() {
		
	}

	/**
	 * 
	 */
	private void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#zoomeLevelChanged(int)
	 */
	@Override
	public void zoomeLevelChanged(int zoom) {
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
