/**
 * 
 */
package client.frame;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shared.communication.DownloadBatch_Params;
import shared.communication.DownloadBatch_Result;
import shared.communication.GetProjects_Params;
import shared.communication.GetProjects_Result;
import shared.communication.GetSampleImage_Params;
import shared.communication.GetSampleImage_Result;
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
	
	private HashMap<String,Integer> projectMap = null;
	private String selectedProject = null;
	private int selProjectId = 0;
	private Frame frame;
	
	public DownloadBatch(Frame frame, boolean isModal, Session s) throws ClientException {
		super(frame, isModal);
		assert s != null;
		
		this.frame = frame;
		this.setTitle("Download Batch");
		//this.setSize(350, 110);
		this.setPreferredSize(new Dimension(350, 110));
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.session = s;
		
		cc = new ClientCommunicator(session.getHost(), session.getPort());
		//cc.setSERVER_HOST(session.getHost());
		//cc.setSERVER_PORT(session.getPort());
		GetProjects_Params param = new GetProjects_Params(session.getUsername(), session.getPassword());
		result = cc.getProjects(param);
		projects = result.getProjects();
		assert projects != null;
		
		//Project Panel
		JPanel projectPanel = new JPanel();
		projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.X_AXIS));
		JLabel prod = new JLabel("Projects: ");
		
		projectMap = new HashMap<String, Integer>();
		String[] sprods = new String[projects.size()];
		for (int i = 0; i < sprods.length; i++) {
			sprods[i] = projects.get(i).getTitle();
			projectMap.put(projects.get(i).getTitle(), projects.get(i).getId());
		}
		box = new JComboBox(sprods);
//		box.addActionListener(actionListener);
		viewSample = new JButton("View Sample");
		viewSample.addActionListener(actionListener);
		
		projectPanel.add(Box.createRigidArea(new Dimension(0,5)));
		projectPanel.add(prod);
		projectPanel.add(Box.createRigidArea(new Dimension(0,prod.getWidth() + 5)));
		projectPanel.add(box);
		projectPanel.add(Box.createRigidArea(new Dimension(0,box.getWidth() + 10)));
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
		
		this.add(rootPanel);
		
	}
	private ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			selectedProject = box.getSelectedItem().toString();
			selProjectId = projectMap.get(selectedProject);
			if (e.getSource() == box) {

			} else if (e.getSource() == viewSample) {

				addSample();
			} else if (e.getSource() == cancel) {
				cancel();
			} else if (e.getSource() == download) {
				download();
			}
			selectedProject = box.getSelectedItem().toString();
			selProjectId = projectMap.get(selectedProject);
		}
	};
	
	private void addSample() {
		assert session.getUsername() != null;
		assert session.getPassword() != null;
		assert selProjectId != 0;
		GetSampleImage_Params imgParam = new GetSampleImage_Params(session.getUsername(), session.getPassword(), selProjectId);
		imgParam.setUrlPrefix(session.getUrlPrefix());
		GetSampleImage_Result result = null;
		try {
			result = cc.getSampleImage(imgParam);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert result != null;
		assert selectedProject != null;
		sample = new SampleImage(frame, result, selectedProject);
		sample.setVisible(true);
	}
	private void cancel() {
		this.dispose();
	}
	private void download() {
		DownloadBatch_Result result = null;
		DownloadBatch_Params params = new DownloadBatch_Params(session.getUsername(), session.getPassword(), selProjectId);
		params.setUrlPrefix(session.getUrlPrefix());
		System.out.println(params.getUrlPrefix());
		try {
			result = cc.downloadBatch(params);
			//System.out.println(result.toString());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.session.setCurrentBatch(result);
		this.dispose();
	}
}
