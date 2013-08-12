/**
 * 
 */
package client.panel;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import client.Session;
import client.frame.Suggestion;
import client.model.Cell;

/**
 * @author zaphinath
 *
 */
@SuppressWarnings("serial")
public class PopUpMenu extends JPopupMenu {
	JMenuItem useSugs;
	private Frame frame;
	private List<String> list;
	private String word;
	private Suggestion suggestion;
	private Cell cell;
	private Session session;
	
	public PopUpMenu(Frame frame, List<String> list, String word, Cell cell, Session session) {
		super();
		this.frame = frame;
		this.list = list;
		this.word = word;
		this.cell = cell;
		this.session = session;
		
		useSugs = new JMenuItem("See Suggestions");
		//useSugs.addMouseListener(mouseListener);
		useSugs.addActionListener(actionListener);
		
		add(useSugs);
	}

	/**
	 * @return the frame
	 */
	public Frame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<String> list) {
		this.list = list;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	private ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			suggestion = new Suggestion(frame, list, word, cell, session);
			suggestion.setVisible(true);
		}
	};
	
	MouseAdapter mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			System.out.println("HULU");
			suggestion = new Suggestion(frame, list, word, cell, session);
			suggestion.setVisible(true);
		}
	};
}
