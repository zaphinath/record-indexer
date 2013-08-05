/**
 * 
 */
package client.panel;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import client.Session;
import client.SessionListener;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class FieldHelpPanel extends JComponent implements SessionListener {
	private Session session;
	private JEditorPane htmlViewer;
	private URL page;
	
	/**
	 * @param session
	 */
	public FieldHelpPanel(Session session) {
		super();
		this.session = session;
		session.addListener(this);
		this.setPreferredSize(new Dimension(650,230));
		if (session.isHaveBatch()){
			try {
				System.out.println("FIELD SELECTED " + session.getSelectedCell().getField());
				page = new URL(session.getUrlPrefix()+session.getFields().get(session.getSelectedCell().getField()-1).getHtmlHelp());
				//htmlViewer.setPage(page);
				htmlViewer = new JEditorPane(page);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			htmlViewer = new JEditorPane();
		}
		htmlViewer.setContentType("text/html");
		//htmlViewer.setEditorKit(new javax.swing.text.html.HTMLEditorKit());
		htmlViewer.setEditable(false);
		//htmlViewer.setOpaque(true);
		htmlViewer.setPreferredSize(new Dimension(650,250));
		//JScrollPane htmlScrollPane = new JScrollPane(htmlViewer);
		//htmlScrollPane.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//htmlScrollPane.setPreferredSize(new Dimension(500, 600));
	
		
		
		this.add(htmlViewer);
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
		/*System.out.println(session.getFields().size()+"fsize");
		try {
			page = new URL(session.getUrlPrefix()+session.getFields().get(newSelectedCell.getField()+1).getHtmlHelp());
			htmlViewer.setPage(page);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
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
