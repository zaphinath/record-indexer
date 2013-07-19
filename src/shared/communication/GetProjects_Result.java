/**
 * 
 */
package shared.communication;

import java.util.List;

import shared.model.Project;

/**
 * @author zaphinath
 *
 */
public class GetProjects_Result {
	private List<Project> projects;
	
	public GetProjects_Result() {
	}
	
	
	/**
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}


	/**
	 * @param projects the projects to set
	 */
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}


	@Override
	public String toString() {
		String tmp = "";
		if (projects != null) {
			for (int i = 0; i < projects.size(); i++) {
				Project project = projects.get(i);
				tmp = tmp + project.getId() + "\n" + project.getTitle() + "\n";
			}
		} else {
			tmp = "FAILED\n";
		}
		return tmp;
	}

}
