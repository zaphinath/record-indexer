/**
 * 
 */
package client.panel;

import java.awt.Frame;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * @author zaphinath
 *
 */
public class PopUpMenu extends JPopupMenu {
	JMenuItem useSugs;
	
	public PopUpMenu(Frame frame, List list, String word) {
		super();
		useSugs = new JMenuItem("See Suggestions");
		add(useSugs);
	}
}
