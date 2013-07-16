package server.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import server.ServerException;
import shared.model.Project;

/**
 * @author zaphinath
 * 
 */
public class ProjectDB {

	private Database db;
	
	/**
	 * Class Constructor
	 * @param db
	 */
	public ProjectDB(Database db) {
		this.db = db;
	}
	
	/**
	 * This queries the database to grab a list of all projects
	 * @return List of all projects
	 * 
	 * @throws ServerException
	 * @throws SQLException 
	 */
	public List<Project> getAll() throws ServerException, SQLException {
		ArrayList<Project> projectList = new ArrayList<Project>();
		PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
    	String sql = "SELECT * FROM projects";
    	stmt = db.getConnection().prepareStatement(sql);
    	
    	rs = stmt.executeQuery();
    	while (rs.next()) {
    		int id = rs.getInt(1);
    		String title = rs.getString(2);
    		int recordsPerImage = rs.getInt(3);
    		int firstYCoord = rs.getInt(4);
    		int recordHeight = rs.getInt(5);
    		Project project = new Project(id, title, recordsPerImage, firstYCoord, recordHeight);
    		projectList.add(project);
    	}
    } catch (SQLException e) {
    	
    } finally {
    	if (rs != null) rs.close();
    	if (stmt != null) stmt.close();
    }
		return projectList;
	}
	
	/**
	 * This adds a project to the database
	 * @param project Project object
	 * @return Project with id returned from the insertion
	 * 
	 * @throws ServerException
	 * @throws SQLException 
	 */
	public Project add(Project project) throws ServerException, SQLException {
	  PreparedStatement stmt = null;
	  Statement keyStmt = null;
	  ResultSet keyRS = null;
	  Project returnProject = null;
	  try {
	    String sql = "INSERT INTO projects (title, records_per_image, first_y_coord, record_height) VALUES(?, ?, ?, ?)";
	    stmt = db.getConnection().prepareStatement(sql);
	    stmt.setString(1, project.getTitle());
	    stmt.setInt(2, project.getRecordsPerImage());
	    stmt.setInt(3, project.getFirstYCoord());
	    stmt.setInt(4, project.getRecordHeight());
	    if (stmt.executeUpdate() == 1) {
	    	keyStmt = db.getConnection().createStatement();
	    	keyRS = keyStmt.executeQuery("SELECT last_insert_rowid()");
	    	keyRS.next();
	    	int projectId = keyRS.getInt(1);
	    	returnProject = new Project(projectId, project.getTitle(), project.getRecordsPerImage(), project.getFirstYCoord(), project.getRecordHeight());
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
	  return returnProject;
	}
	
	/**
	 * This updates a project in the database
	 * @param project
	 * @throws SQLException 
	 */
	public void update(Project project) throws ServerException, SQLException {
	  PreparedStatement stmt = null;
	  Statement keyStmt = null;
	  ResultSet keyRS = null;
	  try {
		  String sql = "UPDATE projects SET title = ?, records_per_image = ?, first_y_coord = ?, record_height = ? where id = ?";
		  stmt = db.getConnection().prepareStatement(sql);
		  stmt.setString(1, project.getTitle());
		  stmt.setInt(2, project.getRecordsPerImage());
		  stmt.setInt(3, project.getFirstYCoord());
		  stmt.setInt(4, project.getRecordHeight());
		  stmt.setInt(5, project.getId());
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
	 * This deletes a project from the database
	 * @param project
	 * @throws ServerException
	 * @throws SQLException 
	 */
	public void delete(Project project) throws ServerException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "DELETE FROM projects WHERE id = ?";
			stmt = db.getConnection().prepareStatement(sql);
			stmt.setInt(1, project.getId());
			
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
