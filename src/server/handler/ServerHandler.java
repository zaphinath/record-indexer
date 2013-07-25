/**
 * Every method in the api needs to validate the user, so in the initialization of the 
 * object we validate the user at that time
 */
package server.handler;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import server.ServerException;
import server.database.Database;
import shared.communication.*;
import shared.model.Batch;
import shared.model.Field;
import shared.model.Project;
import shared.model.RecordValue;
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



	/**
	 * 
	 * @return
	 */
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

	/**
	 * 
	 * @param params
	 * @return
	 */
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
						imageUrl = new URL(params.getUrlPrefix()  + batches.get(i).getFile());
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
		List<Project> projects = null;
		List<RecordValue> recordValues = null;
		URL imageUrl = null;
		db = new Database();
		try {
			db.startTransaction();
			batches = db.getBatchDB().getAll();
			fields = db.getFieldDB().getAll();
			projects = db.getProjectDB().getAll();
			recordValues = db.getRecordValueDB().getAll();
			db.endTransaction(true);
		} catch (ServerException | SQLException e) {
			e.printStackTrace();
		}
		if (isValidUser) {
			if (!hasOpenBatch(batches, accessUser.getID())) {
				List<Field> limitedFields = new ArrayList<Field>();
				List<Batch> limitedBatches = new ArrayList<Batch>();
				Project project = null;

				// limit variables
				for (int i = 0; i < fields.size(); i++) {
					if (fields.get(i).getProjectId() == param.getProjectID()) {
						limitedFields.add(fields.get(i));
					}
				}
				for (int i = 0; i < batches.size(); i++) {
					if (batches.get(i).getAccessUserId() == 0 && batches.get(i).getProjectId() == param.getProjectID()) {
						boolean hasValues = false;
//						System.out.println("user id = 0 && project id is same");
						for (int j = 0; j < recordValues.size(); j++) {
							if (recordValues.get(j).getBatchId() == batches.get(i).getId()) {
								hasValues = true;
							}
						}
						if (hasValues == false){
							limitedBatches.add(batches.get(i));
						}	
					}
				}
				for (int i = 0; i < projects.size(); i++) {
					if (projects.get(i).getId() == param.getProjectID()) {
						project = projects.get(i);
					}
				}
				// if there are no records available to index for project
				if (limitedBatches.size() > 0) {
					Batch selBatch = limitedBatches.get(0);
					
					try {
						imageUrl = new URL(param.getUrlPrefix() + selBatch.getFile());
						dbr.setBatchId(selBatch.getId());
						dbr.setProjectId(param.getProjectID());
						dbr.setImageUrl(imageUrl);
						dbr.setFirstYCoord(project.getFirstYCoord());
						dbr.setRecordHeight(project.getRecordHeight());
						dbr.setNumRecords(project.getRecordsPerImage());
						dbr.setNumFields(limitedFields.size());
						dbr.setUrlPrefix(param.getUrlPrefix());
						for (int i = 0; i < limitedFields.size(); i++) {
							dbr.getFields().add(limitedFields.get(i));
						}					
						
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					selBatch.setAccessUser(accessUser.getID());
					try {
						db.startTransaction();
						db.getBatchDB().updateBatch(selBatch);
						db.endTransaction(true);
					} catch (SQLException e) {
						e.printStackTrace();
						db.endTransaction(false);
					}
				} else {
					dbr.setBatchId(0);
				}
			}
			
		}
		return dbr;
	}



	/**
	 * @param param
	 * @return
	 * @throws MalformedURLException 
	 */
	public Search_Result search(Search_Params param) throws MalformedURLException {
		// TODO Auto-generated method stub
		Search_Result sr = new Search_Result();
		List<Batch> batches = null;
		List<RecordValue> recordValues = null;
		List<RecordValue> limitedValues = new ArrayList<RecordValue>();
		String[] fieldIds = param.getFieldIds().split(",");
		String[] values = param.getSearchValues().split(",");
		
		if (fieldIds.length > 0 && values.length > 0) {
			if (isValidUser) {
				db = new Database();
				try {
					db.startTransaction();
					batches = db.getBatchDB().getAll();
					recordValues = db.getRecordValueDB().getAll();
					db.endTransaction(true);
				} catch (Exception e) {
					db.endTransaction(false);
				}
				// Limit the values to search
				for (int i = 0; i < recordValues.size(); i++) {
					for (int j = 0; j < fieldIds.length; j++) {
						if (recordValues.get(i).getFieldId() == Integer.parseInt(fieldIds[j])) {
							limitedValues.add(recordValues.get(i));
						}		
					}
				}
				// Search each record value and put them in a hashmap
				HashMap<Integer, HashSet<RecordValue>> map = new HashMap<Integer, HashSet<RecordValue>>();
				for (int i = 0; i < limitedValues.size(); i++) {
					for (int j = 0; j < values.length; j++) {
						if (limitedValues.get(i).getValue().toLowerCase().contains(values[j].toLowerCase())) {
							if(map.containsKey(limitedValues.get(i).getFieldId())) {
								map.get(limitedValues.get(i).getFieldId()).add(limitedValues.get(i));
							} else {
								map.put(limitedValues.get(i).getFieldId(), new HashSet<RecordValue>());
								map.get(limitedValues.get(i).getFieldId()).add(limitedValues.get(i));

							}
							
						}
					}
				}
				// Iterate Map and build result structure
				if (map.size() > 0) {
					Iterator<Integer> itr = map.keySet().iterator();
					while(itr.hasNext()) {
	
						int fieldId = itr.next();
						HashSet<RecordValue> altmp = map.get(fieldId);
						Iterator setItr = altmp.iterator();
						while (setItr.hasNext()) {
							Batch batch = null;
							RecordValue rvTmp = (RecordValue) setItr.next();
							try {
								db.startTransaction();
								batch = db.getBatchDB().getBatch(rvTmp.getBatchId());
								db.endTransaction(true);
							} catch (Exception e) {
								db.endTransaction(false);
							}
							Search_Result tmp = new Search_Result();	
							tmp.setBatchId(batch.getId());
							tmp.setImageUrl(new URL(param.getUrlPrefix()+batch.getFile()));
							tmp.setRecordNumber(rvTmp.getRecordNumber());
							tmp.setFieldId(fieldId);
							sr.getList().add(tmp);
						}
					}
				}
			}
		}
		return sr;
	}


	/**
	 * @param param
	 * @return
	 */
	public GetFields_Result getFields(GetFields_Params param) {
		GetFields_Result gfr = new GetFields_Result();
		List<Field> fields = null;
		db = new Database();
		try {
			db.startTransaction();
			fields = db.getFieldDB().getAll();
			db.endTransaction(true);
		} catch (Exception e) {
			db.endTransaction(false);
		}
		if (isValidUser) {
			if (param.getStringProjectID() == null || param.getStringProjectID() == "") {
				for (int i = 0; i < fields.size(); i++) {
					gfr.getFields().add(fields.get(i));
				}
			} else {
				for (int i = 0; i < fields.size(); i++) {
					if (fields.get(i).getProjectId() == param.getProjectID()) {
						gfr.getFields().add(fields.get(i));
					}
				}
			}
		}
		return gfr;
	}


	/**
	 * @param param
	 * @return
	 */
	public SubmitBatch_Result submitBatch(SubmitBatch_Params param) {
		// TODO Auto-generated method stub
		SubmitBatch_Result sbr = new SubmitBatch_Result();
		List<Batch> batches = null;
	//	List<RecordValue> recordValues = null;
		List<Field> fields = null;
		List<Field> limitedFields = new ArrayList<Field>();
		List<Project> projects = null;
		String[] values = param.getRecordValues().split(",|;");
		if (isValidUser) {
			db = new Database();
			try {
				db.startTransaction();
				batches = db.getBatchDB().getAll();
	//			recordValues = db.getRecordValueDB().getAll();
				fields = db.getFieldDB().getAll();
				projects = db.getProjectDB().getAll();
				db.endTransaction(true);
			} catch (Exception e) {
				db.endTransaction(false);
			}
			Batch batch = new Batch();
			Project project = new Project();
			for (int i = 0; i < batches.size(); i++) {
				if (batches.get(i).getId() == param.getBatchID()) {
					batch = batches.get(i);
				}
			}
			if (batch.getAccessUserId() == this.accessUser.getID()) {
				for (int i = 0; i < projects.size(); i++) {
					if (projects.get(i).getId() == batch.getProjectId()) {
						project = projects.get(i);
					}
				}
				batch.setAccessUser(0);
				for (int i = 0; i < fields.size(); i++) {
					if (fields.get(i).getProjectId() == batch.getProjectId()) {
						limitedFields.add(fields.get(i));
					}
				}
				//update database
				try {
					db.startTransaction();
					int count = 0;
					int count2 = 1;
					for (int i = 0; i < values.length; i++) {
						if (count == limitedFields.size()) {
							count = 0;
							count2++;
						}

						RecordValue tmp = new RecordValue(-1, batch.getId(), limitedFields.get(count).getId(), values[i],count2);
						db.getRecordValueDB().addRecordValue(tmp);
						count++;
					}
					accessUser.setIndexedRecords(accessUser.getIndexedRecords() + project.getRecordsPerImage());
					db.getUserDB().update(accessUser);
					db.getBatchDB().updateBatch(batch);
					db.endTransaction(true);
					sbr.setSubmitted(true);
				} catch (Exception e) {
					db.endTransaction(false);
					e.printStackTrace();
				}
			} else {
				sbr.setSubmitted(false);
			}
		}
		return sbr;
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
