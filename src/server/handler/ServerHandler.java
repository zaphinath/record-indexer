/**
 * 
 */
package server.handler;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import server.ServerException;
import server.database.Database;
import shared.communication.*;
import shared.model.Batch;
import shared.model.Field;
import shared.model.Project;
import shared.model.User;

/**
 * @author zaphinath
 *
 */
public class ServerHandler {
	
	private List<User> users;
//	private ValidateUser_Params param;
	private boolean isValidUser;
	private User accessUser;
	private Database db;
	
	public ServerHandler(ValidateUser_Params param) {
		db = new Database();
		isValidUser = false;
//		System.out.println(param.getUsername() + " " + param.getPassword());
		try {
			Database.initialize();
			db.startTransaction();
			users = db.getUserDB().getAll();
			db.endTransaction(true);
		} catch (ClassNotFoundException | ServerException | SQLException e) {
			e.printStackTrace();
		}	
		for (int i = 0; i < users.size(); i++) {
			User tmp = users.get(i);
			if (tmp.getUsername().equals(param.getUsername())) {
				if (tmp.getPassword().equals(param.getPassword())) {
					isValidUser = true;
					accessUser = tmp;
				}
			}
		}
	}
	
	
	/**
	 * @return the isValidUser
	 */
	public boolean isValidUser() {
		return isValidUser;
	}

	/**
	 * @param isValidUser the isValidUser to set
	 */
	public void setValidUser(boolean isValidUser) {
		this.isValidUser = isValidUser;
	}
	
	/**
	 * @return the accessUser
	 */
	public User getAccessUser() {
		return accessUser;
	}

	/**
	 * @param accessUser the accessUser to set
	 */
	public void setAccessUser(User accessUser) {
		this.accessUser = accessUser;
	}



	public ValidateUser_Result validateUser() {
		ValidateUser_Result vvr = new ValidateUser_Result();
		if (isValidUser) {
			vvr.setBool("TRUE\n");
			vvr.setFirstName(accessUser.getFirstName()+"\n");
			vvr.setLastName(accessUser.getLastName()+"\n");
			vvr.setIndexedRecords(accessUser.getIndexedRecords()+"\n");
		}
		return vvr;
	}

	public GetProjects_Result getProjects(GetProjects_Params params) {
		GetProjects_Result gpr = new GetProjects_Result();
		List<Project> projects = null;
		db = new Database();
		try {
			db.startTransaction();
			projects = db.getProjectDB().getAll();
			db.endTransaction(true);
		} catch (ServerException | SQLException e) {
			e.printStackTrace();
		}
		if (isValidUser) {
			gpr.setProjects(projects);
		}
		return gpr;
	}
	
	public GetSampleImage_Result getSampleImage(GetSampleImage_Params params) {
		GetSampleImage_Result gsir = new GetSampleImage_Result();
		List<Batch> batches = null;
		URL imageUrl = null;
		db = new Database();
		try {
			db.startTransaction();
			batches = db.getBatchDB().getAll();
			db.endTransaction(true);
		} catch (ServerException | SQLException e) {
			e.printStackTrace();
		}
		if (isValidUser) {
			for (int i = 0; i < batches.size(); i++) {
				if (batches.get(i).getProjectId() == params.getProjectID()) {
//					System.out.println("HERE at line 126");
					try {
						imageUrl = new URL(params.getUrlPrefix() + "/files" + batches.get(i).getFile());
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					break;
				} 
			}
			gsir.setImageUrl(imageUrl);
		}
		return gsir;
	}


	/**
	 * @param param
	 * @return
	 */
	//@SuppressWarnings("null")
	public DownloadBatch_Result downloadBatch(DownloadBatch_Params param) {
		DownloadBatch_Result dbr = new DownloadBatch_Result();
		
		List<Batch> batches = null;
		List<Field> fields = null;
		db = new Database();
		try {
			db.startTransaction();
			batches = db.getBatchDB().getAll();
			fields = db.getFieldDB().getAll();
			db.endTransaction(true);
		} catch (ServerException | SQLException e) {
			e.printStackTrace();
		}
		if (isValidUser) {
			/*if (!hasOpenBatch(batches, accessUser.getID())) {
				List<Field> limitedFields = null;
				for (int i = 0; i < fields.size(); i++) {
					if (fields.get(i).getProjectId() == param.getProjectID()) {
						limitedFields.add(fields.get(i));
					}
				}
				try {
					//dbr.setFields(limitedFields);
					dbr.setFirstYCoord(0);
					dbr.setImageUrl(new URL(param.getUrlPrefix()));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}*/
			
		}
		return dbr;
	}



	/**
	 * @param param
	 * @return
	 */
	public Search_Result search(Search_Params param) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @param param
	 * @return
	 */
	public GetFields_Result getFields(GetFields_Params param) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @param param
	 * @return
	 */
	public SubmitBatch_Result submitBatch(SubmitBatch_Params param) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Returns true if the user has an open batch at the moment
	 * @param batches
	 * @param userId
	 * @return
	 */
	private boolean hasOpenBatch(List<Batch> batches, int userId) {
		boolean result = false;
		for (int i = 0; i < batches.size(); i++) {
			if (batches.get(i).getAccessUserId() == userId) {
				result = true;
			}
		}
		return result;
	}
}
