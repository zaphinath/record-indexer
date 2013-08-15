/**
 * 
 */
package client.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	
	private Frame frame;
	
	
	public FormEntryPanel(Frame frame, Session s) {
		this.session = s;
		session.addListener(this);
		this.frame = frame;
		
		rootPanel = new JPanel(new BorderLayout());
		rightSide = new JPanel(new GridLayout(0,2));

		lModel = new ListModel(session);
		list = new JList(lModel);
		list.setPreferredSize(new Dimension(80,160));
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				repaint();
			}
			
		});
		values = new ArrayList<JTextField>();
		
		if(session.isHaveBatch()) {
			initializeWithBatch();
		}

		list.addMouseListener(listMouseListener);
	
		//rightSide.addKeyListener(keyboardListener);
		
		rootPanel.add(list, BorderLayout.WEST);
		rootPanel.add(rightSide, BorderLayout.EAST);
		this.add(rootPanel);
	}
	
	private void initializeWithBatch() {
		for (int i = 0; i < session.getFields().size(); i++ ) {
			JLabel tmpLabel = new JLabel(session.getFields().get(i).getTitle());
			rightSide.add(tmpLabel);
			values.add(new JTextField(){ 
				public boolean isManagingFocus() { return true; } });
			rightSide.add(values.get(i));
			//values.get(i).setSize(150, 15);
			values.get(i).setPreferredSize(new Dimension(150,10));
			values.get(i).addMouseListener(listMouseListener);
			values.get(i).addKeyListener(keyboardListener);
			//values.get(i).action(evt, what);
			if (session.getSelectedCell().getField() == i) {
				values.get(i).selectAll();
			}
		}
		for (int i = 0; i < session.getNumRecords(); i++) {
			if (i == session.getSelectedCell().getRecord()) {
				for (int j = 1; j < session.getNumFields(); j++) {
					values.get(j-1).setText(session.getValue(j, i));
					if (session.getKnownWordAt(j, i).known == false) {
						values.get(j-1).setBackground(Color.RED);
					}
	   		}
			}
		}
		list.setSelectedIndex(session.getSelectedCell().getRecord());
		repaint();
		
	}
	
	/**
	 * @return the values
	 */
	public ArrayList<JTextField> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(ArrayList<JTextField> values) {
		this.values = values;
	}

	/**
	 * @return the rootPanel
	 */
	public JPanel getRootPanel() {
		return rootPanel;
	}

	/**
	 * @param rootPanel the rootPanel to set
	 */
	public void setRootPanel(JPanel rootPanel) {
		this.rootPanel = rootPanel;
	}

	/**
	 * @return the rightSide
	 */
	public JPanel getRightSide() {
		return rightSide;
	}

	/**
	 * @param rightSide the rightSide to set
	 */
	public void setRightSide(JPanel rightSide) {
		this.rightSide = rightSide;
	}

	/**
	 * @return the lModel
	 */
	public ListModel getlModel() {
		return lModel;
	}

	/**
	 * @param lModel the lModel to set
	 */
	public void setlModel(ListModel lModel) {
		this.lModel = lModel;
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
		list.setSelectedIndex(cell.getRecord());
		int field = cell.getField()-1;
		values.get(field).requestFocus();
		values.get(field).setText(newValue);
		System.out.println("cell record" + cell.getRecord());
		if (session.getKnownWordAt(cell.getField(), cell.getRecord()).known == false) {
			values.get(field).setBackground(Color.RED);
		} else {
			values.get(field).setBackground(Color.WHITE);
		}
		this.repaint();
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#selectedCellChanged(client.model.Cell)
	 */
	@Override
	public void selectedCellChanged(Cell newSelectedCell) {
		list.setSelectedIndex(newSelectedCell.getRecord());
		values.get(newSelectedCell.getField()).requestFocus();
		values.get(newSelectedCell.getField()-1).requestFocusInWindow();
		if (session.getKnownWordAt(newSelectedCell.getField(), newSelectedCell.getRecord()).known == false) {
			values.get(newSelectedCell.getField()-1).setBackground(Color.RED);
		} else {
			values.get(newSelectedCell.getField()-1).setBackground(Color.WHITE);
		}
		repaint();
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#toggleHighlightsChanged(boolean)
	 */
	@Override
	public void toggleHighlightsChanged(boolean toggle) {
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#imageInversionChanged(boolean)
	 */
	@Override
	public void imageInversionChanged(boolean inversion) {		
	}


	/* (non-Javadoc)
	 * @see client.SessionListener#scaleChanged(double)
	 */
	@Override
	public void scaleChanged(double scale) {
	}
	
	MouseAdapter listMouseListener = new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {  
  	 int row = list.getSelectedIndex();
  	 int col = session.getSelectedCell().getField();
  	 for (int i = 0; i < values.size(); i++) {
  		 if (e.getSource() == values.get(i)) {
  			 col = i+1;
  		 }
  	 }
     session.setSelectedCell(new Cell(col, row));
     for (int i = 0; i < session.getNumRecords(); i++) {
    	 if (i == row) {
    		 for (int j = 1; j < session.getNumFields(); j++) {
    			 values.get(j-1).setText(session.getValue(j, i));
    		 }
    	 }
     }
     if (SwingUtilities.isRightMouseButton(e) && session.getKnownWordAt(col, row).known == false) {
     	PopUpMenu menu = new PopUpMenu(frame, session.getKnownWordAt(col, row).similarValues, 
     			session.getValue(col, row), new Cell(col, row), session);
     	menu.show(e.getComponent(), e.getX(), e.getY());
     }
   }
 };
 
 KeyAdapter keyboardListener = new KeyAdapter() {
//   @Override
//   public void keyReleased(KeyEvent e) {
//	   if(e.getKeyCode() == 157 || e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_TAB) {
//		   int row = list.getSelectedIndex();
//		   int col = session.getSelectedCell().getField();
//		   for (int i = 0; i < values.size(); i++) {
//			   if (e.getSource() == values.get(i)) {
//				   col = i+1;
//			   }
//		   }
//		   session.setSelectedCell(new Cell(col,row));
//	   }
//   	}
   @Override
   public void keyPressed(KeyEvent e) {
	   int row = list.getSelectedIndex();
	   int col = session.getSelectedCell().getField();
	   if (col < 0) col = 0;
	   if (row < 0) row = 0;
//	   if (e.getKeyCode() == KeyEvent.VK_TAB  e.getKeyCode() == KeyEvent.VK_SHIFT) {
//		   for (int i = 0; i < values.size(); i++) {
//			   if (e.getSource() == values.get(i)) {
//				   col = i+1;
//				   if (i < values.size())
//					   values.get(i+1).requestFocusInWindow();
//			   }
//		   }
//		   session.setSelectedCell(new Cell(col,row));
//	   }
	   if(e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER) {
		   for (int i = 0; i < values.size(); i++) {
			   if (e.getSource() == values.get(i)) {
				   String value = values.get(i).getText();
				   session.setValue(col, row, value);
				   if (col < values.size()) {
					   col = i+2;
					   values.get(i+1).requestFocusInWindow();
				   }
			   }
		   }
		   session.setSelectedCell(new Cell(col,row));
	   }
  	}
 };
}
