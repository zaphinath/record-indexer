/**
 * 
 */
package client.frame;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import shared.communication.GetProjects_Params;
import shared.communication.GetProjects_Result;
import shared.model.Project;
import client.ClientException;
import client.Session;
import client.communication.ClientCommunicator;

/**
 * @author Derek Carr
 *
 */
public class DownloadBatch extends JDialog {

	private ClientCommunicator cc;
	private Session session;
	private List<Project> projects;
	private GetProjects_Result result;
	
	public DownloadBatch(Session s) throws ClientException {
		this.setModal(true);
		this.setSize(300,150);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.setTitle("Download Batch");
		this.session = s;
		cc = new ClientCommunicator();
		GetProjects_Params param = new GetProjects_Params(session.getUsername(), session.getPassword());
		result = cc.getProjects(param);
		projects = result.getProjects();
		
		JLabel prod = new JLabel("Projects: ");
		this.add(prod);
		
		//JComboBox box = new JComboBox(projects.toArray());
		//this.add(box);
	}
}
