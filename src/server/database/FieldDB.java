package server.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import server.ServerException;
import shared.model.Field;
import shared.model.Project;
import shared.model.RecordValue;

/**
 * @author zaphinath
 *
 */
public class FieldDB {

	private Database db;
	
	/**
	 * Class Constructor
	 * @param db
	 */
	public FieldDB(Database db) {
		this.db = db;		
	}
	
	/**
	 * Queries the database for all fields and returns them in a list
	 * @return List of fields
	 * @throws ServerException
	 * @throws SQLException 
	 */
	public List<Field> getall() throws ServerException, SQLException {
		ArrayList<Field> projectList = new ArrayList<Field>();
		PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
    	String sql = "SELECT * FROM fields";
    	stmt = db.getConnection().prepareStatement(sql);
    	
    	rs = stmt.executeQuery();
    	while (rs.next()) {
    		int id = rs.getInt(1);
    		int projectId = rs.getInt(2);
    		String value = rs.getString(3);
    		int xCoord = rs.getInt(4);
    		int width = rs.getInt(5);
    		String helpHtml = rs.getString(6);
    		String knownData = rs.getString(7);
    		Field recordField = new Field(id, projectId, value, xCoord, width, helpHtml, knownData);
    		projectList.add(recordField);
    	}
    } catch (SQLException e) {
    	
    } finally {
    	if (rs != null) rs.close();
    	if (stmt != null) stmt.close();
    }
		return projectList;
	}
	
	/**
	 * Adds a field to the database
	 * @param field
	 * @throws ServerException
	 * @throws SQLException 
	 */
	public Field add(Field field) throws ServerException, SQLException {
	  PreparedStatement stmt = null;
	  Statement keyStmt = null;
	  ResultSet keyRS = null;
	  Field returnField = null;
	  try {
	    String sql = "INSERT INTO fields (project_id, title, xcoord, width, help_html, known_data) VALUES(?, ?, ?, ?, ?, ?)";
	    stmt = db.getConnection().prepareStatement(sql);
	    stmt.setInt(1, field.getProjectId());
	    stmt.setString(2, field.getTitle());
	    stmt.setInt(3, field.getXcoord());
	    stmt.setInt(4, field.getWidth());
	    stmt.setString(5, field.getHtmlHelp());
	    stmt.setString(6, field.getKnownData());
	    if (stmt.executeUpdate() == 1) {
	    	keyStmt = db.getConnection().createStatement();
	    	keyRS = keyStmt.executeQuery("SELECT last_insert_rowid()");
	    	keyRS.next();
	    	int fieldId = keyRS.getInt(1);
	    	returnField = new Field(fieldId, field.getProjectId(), field.getTitle(), field.getXcoord(), 
	    													field.getWidth(), field.getHtmlHelp(), field.getKnownData());
	    } else {
	      System.out.println("Insert user failed");
	    }
	  } catch (SQLException e) {
	    e.printStackTrace();
	  } finally {
	    if (stmt != null) stmt.close();
	    if (keyRS != null) keyRS.close();
	    if (keyStmt != null) keyStmt.close();
	  }
	  return returnField;
	}
	
	/**
	 * Updates an existing field in the database
	 * @param field
	 * @throws ServerException
	 * @throws SQLException 
	 */
	public void update(Field field) throws ServerException, SQLException {
		 PreparedStatement stmt = null;
		  Statement keyStmt = null;
		  ResultSet keyRS = null;
		  try {
			  String sql = "UPDATE fields SET project_id = ? title = ?, xcoord = ?, "+
			  						 "width = ?, help_html = ?, known_data = ? where id = ?";
			  stmt = db.getConnection().prepareStatement(sql);
			  stmt.setInt(1, field.getProjectId());
			  stmt.setString(2, field.getTitle());
			  stmt.setInt(3, field.getXcoord());
			  stmt.setInt(4, field.getWidth());
			  stmt.setString(5, field.getHtmlHelp());
			  stmt.setString(6, field.getKnownData());
			  stmt.setInt(7, field.getId());
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
	 * Deletes a field from the database
	 * @param field
	 * @throws ServerException
	 * @throws SQLException 
	 */
	public void delete(Field field) throws ServerException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "DELETE FROM fields WHERE id = ?";
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setInt(1, field.getId());
			
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
