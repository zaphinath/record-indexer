/**
 * 
 */
package client.component;

import javax.swing.JComponent;

import client.Session;
import client.SessionListener;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
public class FieldHelpComponent extends JComponent implements SessionListener {

	/**
	 * @param session
	 */
	public FieldHelpComponent(Session session) {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		// TODO Auto-generated method stub

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

}
