/**
 * 
 */
package client.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


import client.Session;
import client.SessionListener;
import client.component.ListModel;
import client.model.Cell;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class FormEntryPanel extends JPanel implements SessionListener {
	private Session session;
	private ArrayList<JTextField> values;
	
	private JPanel rootPanel;
	private JPanel rightSide;
	
	public FormEntryPanel(Session s) {
		this.session = s;
		session.addListener(this);
		
		rootPanel = new JPanel(new BorderLayout());
		rightSide = new JPanel(new GridLayout(0,2));

		ListModel lModel = new ListModel(session);
		JList list = new JList(lModel);
		list.setSize(new Dimension(30,230));
		
		
		
		values = new ArrayList<JTextField>();
		
		if(session.isHaveBatch()) {
			initializeWithBatch();
		}

		
		rootPanel.add(list, BorderLayout.WEST);
		rootPanel.add(rightSide, BorderLayout.EAST);
		this.add(rootPanel);
	}
	
	private void initializeWithBatch() {
		for (int i = 0; i < session.getFields().size(); i++ ) {
			JLabel tmpLabel = new JLabel(session.getFields().get(i).getTitle());
			rightSide.add(tmpLabel);
			values.add(new JTextField());
			rightSide.add(values.get(i));
			
		}
	}
	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		if (session.isHaveBatch()) {
			initializeWithBatch();
		} else {
			values.clear();
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
	}
}
