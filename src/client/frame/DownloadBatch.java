/**
 * 
 */
package client.frame;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shared.communication.GetProjects_Params;
import shared.communication.GetProjects_Result;
import shared.communication.GetSampleImage_Params;
import shared.model.Project;
import client.ClientException;
import client.Session;
import client.communication.ClientCommunicator;

/**
 * @author Derek Carr
 *
 */
@SuppressWarnings("serial")
public class DownloadBatch extends JDialog {

	private ClientCommunicator cc;
	private Session session;
	private List<Project> projects;
	private GetProjects_Result result;
	private JButton viewSample;
	private JComboBox box;
	private JButton cancel;
	private JButton download;
	private SampleImage sample;
	private String selectedProject;
	
	public DownloadBatch(Frame frame, boolean isModal, Session s) throws ClientException {
		super(frame, isModal);
		this.setTitle("Download Batch");
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.session = s;
		
		cc = new ClientCommunicator();
		cc.setSERVER_HOST(session.getHost());
		cc.setSERVER_PORT(session.getPort());
		GetProjects_Params param = new GetProjects_Params(session.getUsername(), session.getPassword());
		result = cc.getProjects(param);
		projects = result.getProjects();
		
		//Project Panel
		JPanel projectPanel = new JPanel();
		projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.X_AXIS));
		JLabel prod = new JLabel("Projects: ");
		box = new JComboBox(projects.toArray());
//		box.addActionListener(actionListener);
		viewSample = new JButton("View Sample");
		viewSample.addActionListener(actionListener);
		
		projectPanel.add(Box.createRigidArea(new Dimension(0,5)));
		projectPanel.add(prod);
		projectPanel.add(Box.createRigidArea(new Dimension(0,5)));
		projectPanel.add(box);
		projectPanel.add(Box.createRigidArea(new Dimension(0,5)));
		projectPanel.add(viewSample);
		
		
		//Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		cancel = new JButton("Cancel");
		cancel.addActionListener(actionListener);
		download = new JButton("Download");
		download.addActionListener(actionListener);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(0,20)));
		buttonPanel.add(cancel);
		buttonPanel.add(Box.createRigidArea(new Dimension(0,5)));
		buttonPanel.add(download);
		
		//Root Panel
		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
		
		rootPanel.add(projectPanel);
		rootPanel.add(Box.createRigidArea(new Dimension(0,5)));
		rootPanel.add(buttonPanel);
		rootPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
	}
	private ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == box) {
				selectedProject = box.getSelectedItem().toString();
			} else if (e.getSource() == viewSample) {
				addSample();
			} else if (e.getSource() == cancel) {
				cancel();
			} else if (e.getSource() == download) {
				download();
			}
		}
	};
	
	private void addSample() {
		GetSampleImage_Params imgParam = new GetSampleImage_Params();
		sample = new SampleImage(cc, imgParam, this.selectedProject);
		sample.setVisible(true);
	}
	private void cancel() {
		this.dispose();
	}
	private void download() {
		
	}
}
