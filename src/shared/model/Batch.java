/**
 * 
 */
package shared.model;

/**
 * @author Derek Carr
 *
 */
public class Batch {
	
	private int id;
	private int projectId;
	private String file;
	
	/**
	 * Class Constructor
	 */
	public Batch() {
		this.id = -1;
		this.projectId = -1;
		this.file = null;
	}
	
	/**
	 * Class Constructor with params
	 * @param id
	 * @param projectId
	 * @param file
	 */
	public Batch(int id, int projectId, String file) {
		this.id = id;
		this.projectId = projectId;
		this.file = file;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}
	

}
