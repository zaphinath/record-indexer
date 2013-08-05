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
		session.addListener(this);
		if (session.isHaveBatch()) {
			initialize();
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		if (session.isHaveBatch()) {
			//System.out.println(session.getNumRecords()+"INTABLE");
			return session.getNumRecords();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		if (session.isHaveBatch()) {
			//System.out.println("GETCOLCOUNT" + session.getNumFields());
			return session.getNumFields();
		}
		return 0;		
	}
	
	@Override
	public String getColumnName(int column) {

		String result = null;
		if (column >= 0 && column < getColumnCount()) {
			result = session.getFieldTitles().get(column);
		} else {
			throw new IndexOutOfBoundsException();
		}
		//System.out.println("Result="+result);
		return result;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return (column == 0) ? false : true;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		session.setSelectedCell(new Cell(columnIndex, rowIndex));
		return session.getValue( columnIndex, rowIndex);
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		session.setValue(column, row, (String)value);
	}
	
	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		System.out.println("BATCH CHANGED:");
		fireTableStructureChanged();
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
		fireTableCellUpdated(cell.getField(), cell.getRecord());
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#selectedCellChanged(client.model.Cell)
	 */
	@Override
	public void selectedCellChanged(Cell cell) {
		
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
