/**
 * 
 */
package server.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import server.ServerException;
import shared.model.Batch;

/**
 * @author Derek Carr
 *
 */
public class BatchDB {

		private Database db;
		
		/**
		 * Class Constructor;
		 * @param db
		 */
		public BatchDB(Database db) {
			this.db = db;
		}
		
		/**
		 * Builds a list of all batches in the database
		 * @return List of all batches
		 * @throws ServerException
		 * @throws SQLException
		 */
		public List<Batch> getAll() throws ServerException, SQLException {
			ArrayList<Batch> projectList = new ArrayList<Batch>();
			PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	    	String sql = "SELECT * FROM batches";
	    	stmt = db.getConnection().prepareStatement(sql);
	    	
	    	rs = stmt.executeQuery();
	    	while (rs.next()) {
	    		int id = rs.getInt(1);
	    		int projectId = rs.getInt(2);
	    		String file = rs.getString(3);
	    		int accessUserId = rs.getInt(4);
	    		Batch batch = new Batch(id, projectId, file, accessUserId);
	    		projectList.add(batch);
	    	}
	    } catch (SQLException e) {
	    	
	    } finally {
	    	if (rs != null) rs.close();
	    	if (stmt != null) stmt.close();
	    }
			return projectList;
		}
		
		/**
		 * Adds a Batch object to the database 
		 * @param batch
		 * @return batch with id set to same as in database
		 * @throws SQLException
		 */
		public Batch addBatch(Batch batch) throws SQLException {
		  PreparedStatement stmt = null;
		  Statement keyStmt = null;
		  ResultSet keyRS = null;
		  Batch returnBatch = null;
		  try {
		    String sql = "INSERT INTO batches (project_id, image_file) VALUES(?, ?)";
		    stmt = db.getConnection().prepareStatement(sql);
		    stmt.setInt(1, batch.getProjectId());
		    stmt.setString(2, batch.getFile());
		    if (stmt.executeUpdate() == 1) {
		    	keyStmt = db.getConnection().createStatement();
		    	keyRS = keyStmt.executeQuery("SELECT last_insert_rowid()");
		    	keyRS.next();
		    	int batchId = keyRS.getInt(1);
		    	returnBatch = new Batch(batchId, batch.getProjectId(), batch.getFile(), batch.getAccessUserId());
		    } else {
		      System.out.println("Insert batch failed");
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    if (stmt != null) stmt.close();
		    if (keyRS != null) keyRS.close();
		    if (keyStmt != null) keyStmt.close();
		  }
		  return returnBatch;
		}
		
		/**
		 * Updates a batch in the database
		 * @param batch
		 * @throws SQLException
		 */
		public void updateBatch(Batch batch) throws SQLException {
			PreparedStatement stmt = null;
		  Statement keyStmt = null;
		  ResultSet keyRS = null;
		  try {
			  String sql = "UPDATE batches SET project_id = ?, image_file = ?, activeUserId = ? where id = ?";
			  stmt = db.getConnection().prepareStatement(sql);
			  stmt.setInt(1, batch.getProjectId());
			  stmt.setString(2, batch.getFile());
			  stmt.setInt(3, batch.getAccessUserId());
			  stmt.setInt(4, batch.getId());
			  if (stmt.executeUpdate() == 1) {
				  System.out.println("SUCCESS");
			  } else {
				  System.out.println("UPDATE failed");
			  }
		  } catch (SQLException e) { e.printStackTrace(); }
		  finally {
			  if (stmt != null) stmt.close();
			  if (keyRS != null) keyRS.close();
			  if (keyStmt != null) keyStmt.close();
		  }
		}
		
		/**
		 * Deletes a batch from the database
		 * @param batch
		 * @throws SQLException
		 */
		public void deleteBatch(Batch batch) throws SQLException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				String sql = "DELETE FROM batches WHERE id = ?";
				stmt = db.getConnection().prepareStatement(sql);
				stmt.setInt(1, batch.getId());
				
				if (stmt.executeUpdate() == 1) {
					//Success
				} else {
					//Fail
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (stmt != null) stmt.close();
				if (rs != null) rs.close();
			}
		}
}
