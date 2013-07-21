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
import shared.model.RecordValue;

/**
 * @author Derek Carr
 *
 */
public class RecordValueDB {
	
	private Database db;
	
	/**
	 * Class Constructor
	 * @param db
	 */
	public RecordValueDB(Database db) {
		this.db = db;
	}
	
	/**
	 * List of all RecordValues in the database
	 * @return List<RecordValue> 
	 * @throws ServerException
	 * @throws SQLException
	 */
	public List<RecordValue> getAll() throws ServerException, SQLException {
		ArrayList<RecordValue> projectList = new ArrayList<RecordValue>();
		PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
    	String sql = "SELECT * FROM record_values";
    	stmt = db.getConnection().prepareStatement(sql);
    	
    	rs = stmt.executeQuery();
    	while (rs.next()) {
    		int id = rs.getInt(1);
    		int batchId = rs.getInt(2);
    		int fieldId = rs.getInt(3);
    		String value = rs.getString(4);
    		int recordNumber = rs.getInt(5);
    		RecordValue recordValue = new RecordValue(id, batchId, fieldId, value, recordNumber);
    		projectList.add(recordValue);
    	}
    } catch (SQLException e) {
    	
    } finally {
    	if (rs != null) rs.close();
    	if (stmt != null) stmt.close();
    }
		return projectList;
	}
	
	/**
	 * Adds a RecordValue to the database
	 * @param recordValue
	 * @return RecordValue with updated ID from the database
	 * @throws SQLException
	 */
	public RecordValue addRecordValue(RecordValue recordValue) throws SQLException {
	  PreparedStatement stmt = null;
	  Statement keyStmt = null;
	  ResultSet keyRS = null;
	  RecordValue returnRecordValue = null;
	  try {
	    String sql = "INSERT INTO record_values (batch_id, field_id, value, record_number) VALUES(?, ?, ?, ?)";
	    stmt = db.getConnection().prepareStatement(sql);
	    stmt.setInt(1, recordValue.getBatchId());
	    stmt.setInt(2, recordValue.getFieldId());
	    stmt.setString(3, recordValue.getValue());
	    stmt.setInt(4, recordValue.getRecordNumber());
	    if (stmt.executeUpdate() == 1) {
	    	keyStmt = db.getConnection().createStatement();
	    	keyRS = keyStmt.executeQuery("SELECT last_insert_rowid()");
	    	keyRS.next();
	    	int recordValueId = keyRS.getInt(1);
	    	returnRecordValue = new RecordValue(recordValueId, recordValue.getBatchId(), recordValue.getFieldId(), 
	    			recordValue.getValue(), recordValue.getRecordNumber());
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
	  return returnRecordValue;
	}
	
	/**
	 * Updates RecordValue in the database
	 * @param recordValue
	 * @throws SQLException
	 */
	public void updateRecordValue(RecordValue recordValue) throws SQLException {
		PreparedStatement stmt = null;
	  Statement keyStmt = null;
	  ResultSet keyRS = null;
	  try {
		  String sql = "UPDATE record_values SET batch_id = ?, field_id = ?, value = ?, record_number = ? where id = ?";
		  stmt = db.getConnection().prepareStatement(sql);
		  stmt.setInt(1, recordValue.getBatchId());
		  stmt.setInt(2, recordValue.getFieldId());
		  stmt.setString(3, recordValue.getValue());
		  stmt.setInt(4, recordValue.getRecordNumber());
		  stmt.setInt(5, recordValue.getId());
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
	 * Deletes a RecordValue from the database
	 * @param recordValue
	 * @throws SQLException
	 */
	public void deleteRecordValue(RecordValue recordValue) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "DELETE FROM record_values WHERE id = ?";
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setInt(1, recordValue.getId());
			
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
