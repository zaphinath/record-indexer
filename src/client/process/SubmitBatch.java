/**
 * 
 */
package client.process;

import java.io.File;

import shared.communication.SubmitBatch_Params;
import shared.communication.SubmitBatch_Result;
import client.ClientException;
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
		file = new File("sessions/"+session.getLastName().toLowerCase().trim()+session.getFirstName().toLowerCase().trim()+".session");
		
	}
	
	public void submit() {
		String path = "./sessions/"+session.getLastName().toLowerCase().trim()+session.getFirstName().toLowerCase().trim()+".session";
		file = new File(path);
		file.delete();
		
		String tmp = "";
		for (int i = 0; i < session.getNumRecords(); i++) {
			for (int j = 1; j < session.getNumFields(); j++) {
				System.out.println(session.getValue(j, i));
				tmp = tmp + session.getValue(j, i) + ",";
			}
		}
		
		SubmitBatch_Params params = new SubmitBatch_Params();
		params.setBatchID(session.getBatchId());
		params.setUsername(session.getUsername());
		params.setPassword(session.getPassword());
		params.setRecordValues(tmp);
		
		try {
			SubmitBatch_Result result = cc.submitBatch(params);
			if (result.toString().toLowerCase().contains("true")) {
				session = new Session();
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
