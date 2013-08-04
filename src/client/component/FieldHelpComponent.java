/**
 * 
 */
package client.component;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JEditorPane;

import client.Session;
import client.SessionListener;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class FieldHelpComponent extends JComponent implements SessionListener {
	private Session session;
	private JEditorPane htmlViewer;
	private URL page;
	
	/**
	 * @param session
	 */
	public FieldHelpComponent(Session session) {
		super();
		this.session = session;
		session.addListener(this);
		this.setPreferredSize(new Dimension(650,250));

		htmlViewer = new JEditorPane();
		htmlViewer.setEditable(false);
		
		if (session.isHaveBatch()){
			try {
				page = new URL(session.getUrlPrefix()+session.getFields().get(session.getSelectedCell().getField()).getHtmlHelp());
				htmlViewer.setPage(page);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
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
		System.out.println(session.getFields().size()+"fsize");
		try {
			page = new URL(session.getUrlPrefix()+session.getFields().get(newSelectedCell.getField()).getHtmlHelp());
			htmlViewer.setPage(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
