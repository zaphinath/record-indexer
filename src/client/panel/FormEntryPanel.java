/**
 * 
 */
package client.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	
	private JList list;
	private ListModel lModel;
	
	public FormEntryPanel(Session s) {
		this.session = s;
		session.addListener(this);
		
		rootPanel = new JPanel(new BorderLayout());
		rightSide = new JPanel(new GridLayout(0,2));

		lModel = new ListModel(session);
		list = new JList(lModel);
		list.setSize(new Dimension(30,230));
		
		
		
		values = new ArrayList<JTextField>();
		
		if(session.isHaveBatch()) {
			initializeWithBatch();
		}

		list.addMouseListener(listMouseListener);
		
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
			
			values.get(i).addMouseListener(listMouseListener);
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
	
	MouseAdapter listMouseListener = new MouseAdapter() {
   @Override
    public void mouseClicked(MouseEvent e) {  
      /*//int row = list.rowAtPoint(e.getPoint());//get mouse-selected row
    	//int col = table.columnAtPoint(e.getPoint());//get mouse-selected col
      int row = list.getSelectedIndex();
      //int col = rightSide.getComponentAt(e.getX(), e.getY());
      int col = 0;
      session.setSelectedCell(new Cell(col,row));*/
  	 int row = list.getSelectedIndex();
  	 int col = 0;
  	 for (int i = 0; i < values.size(); i++) {
  		 if (e.getSource() == values.get(i)) {
  			 col = i+1;
  		 }
  	 }
     session.setSelectedCell(new Cell(col, row));
      
    }
 };
 
 KeyAdapter keyboardListener = new KeyAdapter() {
   @Override
   public void keyPressed(KeyEvent e) {
	   Cell tmp = session.getSelectedCell();
	   Cell nCell = null;
	   if(e.getKeyCode() == KeyEvent.VK_TAB) {
		   if (tmp.getField() < session.getNumFields()-1) {
			   nCell = new Cell(tmp.getField()+1, tmp.getRecord());
		   } else if (tmp.getRecord() == session.getNumRecords() && tmp.getField() == session.getNumFields()) {
			   nCell = new Cell(0,0);
		   } else {
			   nCell = new Cell(0, tmp.getRecord()+1);
		   }
		   session.setSelectedCell(nCell);
	   }
   }
 };
}
