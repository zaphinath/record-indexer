/**
 * 
 */
package client.process;

import java.io.File;

import shared.communication.SubmitBatch_Params;
import client.Session;
import client.communication.ClientCommunicator;

/**
 * @author zaphinath
 *
 */
public class SubmitBatch {
	private ClientCommunicator cc;
	private Session session;
	private File file;
	
	/**
	 * 
	 */
	public SubmitBatch(Session session) {
		this.session = session;
		cc = new ClientCommunicator(session.getHost(), session.getPort());
		
	}
	
	public void submit() {
		String path = "./sessions/"+session.getLastName().toLowerCase().trim()+session.getFirstName().toLowerCase().trim()+".session";
		file = new File(path);
		file.delete();
		SubmitBatch_Params params = new SubmitBatch_Params();
		params.setBatchID(session.getCurrentBatch().getBatchId());
		params.setUsername(session.getUsername());
		params.setPassword(session.getPassword());
		params.setRecordValues(session.getRecordValues());
	}

}
