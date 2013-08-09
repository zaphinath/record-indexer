/**
 * 
 */
package client.frame;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	
	public Suggestion(Frame frame, List list, String word) {
		super(frame, true);
		assert frame != null;
		assert list != null;
		assert word != null;
		
		setLocationRelativeTo(null);
		this.setModal(true);
		this.setTitle("Suggestion for "+word);
		this.setSize(200, 400);
		this.setResizable(false);
		
		cancel = new JButton("Cancel");
		useSuggestion = new JButton("Use Suggestion");
		listSuggestions = new JList(list.toArray());
		
		cancel.addActionListener(actionListener);
		useSuggestion.addActionListener(actionListener);
		
		rootPanel = new JPanel(new BorderLayout());
		
		JPanel south = new JPanel(new BorderLayout());
		south.add(cancel, BorderLayout.EAST);
		south.add(useSuggestion, BorderLayout.WEST);
		
		rootPanel.add(new JScrollPane(listSuggestions), BorderLayout.NORTH);
		rootPanel.add(listSuggestions, BorderLayout.SOUTH);
		
		this.add(rootPanel);
	}
	
	private ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cancel) {
				
			} else if (e.getSource() == useSuggestion) {
				
			}
		}
	};
}
