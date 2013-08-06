/**
 * 
 */
package client.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import client.Session;
import client.SessionListener;
import client.component.TableModel;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class Table extends JPanel implements SessionListener {
	private Session session;
	private JTable table;
	private TableModel tm;
	private Cell selectedCell;
	
	public Table(Session s) {
		super();
		this.session = s;
		session.addListener(this);
		
		selectedCell = session.getSelectedCell();
		tm = new TableModel(session);
		table = new JTable(tm);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setGridColor(Color.BLACK);
		table.addMouseListener(tableMouseListener);
		table.addKeyListener(keyboardListener);
		//table.setColumnSelectionInterval(1, 1);
		//table.setRowSelectionInterval(0, 0);
		if (session.isHaveBatch()) {
			session.setSelectedCell(new Cell(1,0));
		}
		this.setLayout(new BorderLayout());
		
		JScrollPane rPane = new JScrollPane(table);

		this.add(rPane, BorderLayout.CENTER);

	}
	
	MouseAdapter tableMouseListener = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {  
        int row = table.rowAtPoint(e.getPoint());//get mouse-selected row
        int col = table.columnAtPoint(e.getPoint());//get mouse-selected col
        session.setSelectedCell(new Cell(col,row));
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

	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		if (session.isHaveBatch()) { 
			session.setSelectedCell(new Cell(1,0));
		} else {
			session.setSelectedCell(null);
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
		table.setRowSelectionInterval(newSelectedCell.getRecord(), newSelectedCell.getRecord());
		table.setColumnSelectionInterval(newSelectedCell.getField(), newSelectedCell.getField());
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
/*
@SuppressWarnings("serial")
class ColorCellRenderer extends JLabel implements TableCellRenderer {

	private Border unselectedBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
	private Border selectedBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

	public ColorCellRenderer() {
		
		setOpaque(true);
		setFont(getFont().deriveFont(16.0f));
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {

		//Color c = ColorUtils.fromString((String)value);
		//this.setBackground(c);
		
		if (isSelected) {
			this.setBorder(selectedBorder);
		}
		else {
			this.setBorder(unselectedBorder);
		}
		
		this.setText((String)value);
		
		return this;
	}

}
*/