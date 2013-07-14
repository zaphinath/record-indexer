package server.database;

import java.util.ArrayList;
import java.util.List;

import server.ServerException;
import shared.model.Project;

/**
 * @author zaphinath
 * 
 */
public class ProjectDB {

	/**
	 * Class Constructor
	 * @param db
	 */
	public ProjectDB(Database db) {
		
	}
	
	/**
	 * This queries the database to grab a list of all projects
	 * @return List of all projects
	 * 
	 * @throws ServerException
	 */
	public List<Project> getAll() throws ServerException {
		ArrayList<Project> projectList = new ArrayList<Project>();
		
		return projectList;
	}
	
	/**
	 * This adds a project to the database
	 * @param project
	 */
	public void add(Project project) throws ServerException {
		
	}
	
	/**
	 * This updates a project in the database
	 * @param project
	 */
	public void update(Project project) throws ServerException {
		
	}
	
	/**
	 * This deletes a project from the database
	 * @param project
	 * @throws ServerException
	 */
	public void delete(Project project) throws ServerException {
		
	}
}
