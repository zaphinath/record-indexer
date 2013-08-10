/**
 * 
 */
package client.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import client.Session;
import client.Session.KnownWord;
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
	private JFrame frame;
	
	public Table(JFrame frame, Session s) {
		super();
		this.session = s;
		this.frame = frame;
		session.addListener(this);
		
		final ColorCellRenderer renderer = new ColorCellRenderer(session);
		selectedCell = session.getSelectedCell();
		tm = new TableModel(session);
		table = new JTable(tm) {
		   @Override
		   public TableCellRenderer getCellRenderer(int row, int column) {
			    // TODO Auto-generated method stub
			    return renderer;
		   }
		};
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setGridColor(Color.BLACK);
		table.addMouseListener(tableMouseListener);
		table.addKeyListener(keyboardListener);
		/*TableColumnModel columnModel = table.getColumnModel();
		for (int i = 0; i < tm.getColumnCount(); ++i) {
			TableColumn column = columnModel.getColumn(i);
			column.setCellRenderer(new ColorCellRenderer(session));
			//column.setCellEditor(new ColorCellEditor(session));
		}*/
		//table.setDefaultRenderer(String.class, new ColorCellRenderer(session));
		
		if (session.isHaveBatch()) {
			session.setSelectedCell(session.getSelectedCell());
		}
		this.setLayout(new BorderLayout());
		
		JScrollPane rPane = new JScrollPane(table);

		this.add(rPane, BorderLayout.CENTER);

	}
	
	MouseAdapter tableMouseListener = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {  
    	  //if(e.getButton() == MouseEvent.BUTTON1) {
	        int row = table.rowAtPoint(e.getPoint());//get mouse-selected row
	        int col = table.columnAtPoint(e.getPoint());//get mouse-selected col
	        session.setSelectedCell(new Cell(col,row));
    	  if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON2) {
    		 
    		  PopUpMenu menu = new PopUpMenu(frame, session.getKnownWordAt(col, row).similarValues, session.getValue(col, row));
    		  menu.show(e.getComponent(), e.getX(), e.getY());
    	  }
      }
   };
   
   KeyAdapter keyboardListener = new KeyAdapter() {
	   @Override
	   public void keyReleased(KeyEvent e) {
		   Cell tmp = session.getSelectedCell();
		   Cell nCell = null;
		   if(e.getKeyCode() == KeyEvent.VK_TAB) {
		        int row = table.getSelectedRow();//get mouse-selected row
		        int col = table.getSelectedColumn();//get mouse-selected col

		        session.setSelectedCell(new Cell(col,row));
		   }
	   }
   };

	/* (non-Javadoc)
	 * @see client.SessionListener#hasBatchChanged()
	 */
	@Override
	public void hasBatchChanged() {
		
	}

	/* (non-Javadoc)
	 * @see client.SessionListener#valueChanged(client.model.Cell, java.lang.String)
	 */
	@Override
	public void valueChanged(Cell cell, String newValue) {
		System.out.println(session.getValue(cell.getField(), cell.getRecord()));
		System.out.println(session.getKnownWordAt(cell.getField(), cell.getRecord()).known);
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

@SuppressWarnings("serial")
class ColorCellRenderer extends JLabel implements TableCellRenderer {

	private Border unselectedBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
	private Border selectedBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

	private Session session;
	
	public ColorCellRenderer(Session s) {
		this.session = s;
		setOpaque(true);
		//setFont(getFont().deriveFont(16.0f));
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		//KnownWord k = session.getKnownWordAt(column, row);
		if (session.getKnownWordAt(column, row).known == false) {
			this.setBackground(Color.RED);
		} else {
			this.setBackground(Color.WHITE);
		}
		if (isSelected) {
			this.setBorder(selectedBorder);
			this.setBackground(Color.BLUE);
		} else {
			this.setBorder(unselectedBorder);
		}		
		this.setText((String)value);
		
		return this;
	}
}
/*
@SuppressWarnings("serial")
class ColorCellEditor extends AbstractCellEditor implements TableCellEditor {
	
	private JComboBox<String> comboBox;
	private String currentValue;
	private Session session;
	
	
	public ColorCellEditor(Session session) {
		this.session = session;
	}

	@Override
	public Object getCellEditorValue() {
		return currentValue;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		currentValue = (String)value;
		
		comboBox.setSelectedItem(currentValue);
		
		return comboBox;
	}
	
	private ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			currentValue = (String)comboBox.getSelectedItem();
			
			/*Color colorValue = ColorUtils.fromString(comboValue);
			
			if (colorValue == null) {
				if (colorMap.containsKey(comboValue)) {
					colorValue = colorMap.get(comboValue);
				}
			}
			
			if (colorValue != null) {
				currentValue = ColorUtils.toString(colorValue);
			}
			*
			fireEditingStopped();
		}	
	};
	
}*/