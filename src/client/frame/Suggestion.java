/**
 * 
 */
package client.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import client.Session;
import client.model.Cell;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class Suggestion extends JDialog {
	private JButton cancel;
	private JButton useSuggestion;
	private JList listSuggestions;
	private JPanel rootPanel;
	private JScrollPane jsp;
	private Session session;
	private Cell cell;
	
	public Suggestion(Frame frame, List list, String word, Cell cell, Session session) {
		super(frame, true);
		assert frame != null;
		assert list != null;
		assert word != null;
		assert session != null;
		
		this.session = session;
		this.cell = cell;
		
		setLocationRelativeTo(null);
		this.setModal(true);
		this.setTitle("Suggestion for "+word);
		this.setSize(230, 250);
		this.setResizable(false);
		
		cancel = new JButton("Cancel");
		useSuggestion = new JButton("Use Suggestion");
		listSuggestions = new JList(list.toArray());
		listSuggestions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSuggestions.setSize(200, 220);
		
		cancel.addActionListener(actionListener);
		useSuggestion.addActionListener(actionListener);
		
		rootPanel = new JPanel(new BorderLayout());
		
		JPanel south = new JPanel();
		south.setLayout(new BoxLayout(south, BoxLayout.X_AXIS));
		
		south.add(Box.createRigidArea(new Dimension(0,10)));
		south.add(cancel);
		south.add(Box.createRigidArea(new Dimension(0,10)));
		south.add(useSuggestion, BorderLayout.WEST);
		
		jsp = new JScrollPane(listSuggestions);
		
		rootPanel.add(jsp, BorderLayout.NORTH);
		rootPanel.add(south, BorderLayout.SOUTH);
		
		this.add(rootPanel);
	}
	
	private void destroy() {
		this.setVisible(false);
		this.dispose();
	}
	
	private ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cancel) {
				destroy();
			} else if (e.getSource() == useSuggestion) {
				String tmp = listSuggestions.getSelectedValue().toString();
				session.setValue(cell.getField(), cell.getRecord(), tmp);
				destroy();
			}
		}
	};
}
