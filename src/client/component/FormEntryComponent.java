/**
 * 
 */
package client.component;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JTextField;

import client.Session;
import client.SessionListener;
import client.model.Cell;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class FormEntryComponent extends JComponent implements SessionListener {
	private Session session;
	private ArrayList<JTextField> values;
	
	public FormEntryComponent(Session s) {
		this.session = s;
		values = new ArrayList<JTextField>();
		
		for (int i = 0; i < session.getFields().size(); i++ ) {
			values.add(new JTextField());
			this.add(values.get(i));
		}
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


	/* (non-Javadoc)
	 * @see client.SessionListener#scaleChanged(double)
	 */
	@Override
	public void scaleChanged(double scale) {
		// TODO Auto-generated method stub
		scale = session.getScale();
		repaint();
	}
}
