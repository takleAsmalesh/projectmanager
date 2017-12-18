package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import entity.Customer;
import entity.Project;
import manager.ManagerHelper;

@Path("/project")
public class ProjectService {

	@GET
	@Path("/get")
	public Project getProject(@QueryParam("id") int id) {
		return ManagerHelper.getProjectManager().get(id);
	}

	@GET
	@Path("/getByName")
	public List<Project> getByProjectname(@QueryParam("projectname") String projectname) {
		return ManagerHelper.getProjectManager().getByProjectname(projectname);
	}

	@GET
	@Path("/getAllProject")
	public List<Project> getAllProjects() {
		return ManagerHelper.getProjectManager().getAllProjects();
	}

	@GET
	@Path("/getCustomerActiveProjects")
	public List<Project> getCustomerActiveProjects(@QueryParam("customer") int userId) {
		return ManagerHelper.getProjectManager().getCustomerActiveProjects(userId);
	}

	@GET
	@Path("/getActiveProjects")
	public List<Project> getActiveProjects() {
		return (List)ManagerHelper.getProjectManager().getActiveProjects();
	}

	@GET
	@Path("/getProjectBeforeEnd")
	public List<Project> getProjectBeforeEnd() {
		return ManagerHelper.getProjectManager().getProjectBeforeEnd();
	}
	@GET
	@Path("/createProject")
	public Project createProject(@QueryParam("projectname") String projectname,
			@QueryParam("customer") int customer,
			@QueryParam("customerprojectmanager") String customerprojectmanager,
			@QueryParam("projectmanageremail") String projectmanageremail,
			@QueryParam("projectmanagerephone") String projectmanagerephone, @QueryParam("startdate") String startdate,
			@QueryParam("enddate") String enddate) {
		return (Project) ManagerHelper.getProjectManager().createProject(projectname, customer, customerprojectmanager,
				projectmanageremail, projectmanagerephone, startdate, enddate);
	}

	@GET
	@Path("/deleteProject")
	public Reply deleteProject(@QueryParam("id") int id) {
		return ManagerHelper.getProjectManager().deleteProject(id);
	}

	@GET
	@Path("/updateProject")
	public Reply updateProject(@QueryParam("id") int id,
			@QueryParam("projectname") String projectname,
			@QueryParam("customer") int customer,
			@QueryParam("customerprojectmanager") String customerprojectmanager,
			@QueryParam("projectmanageremail") String projectmanageremail,
			@QueryParam("projectmanagerephone") String projectmanagerephone, @QueryParam("startdate") String startdate,
			@QueryParam("enddate") String enddate) {
		return ManagerHelper.getProjectManager().updateProject(id, projectname, customer, customerprojectmanager,
				projectmanageremail, projectmanagerephone, startdate, enddate);
	}
	
	@GET
	@Path("getProjectByEmployee")
	public List<Project> getProjectByEmployee(@QueryParam("id") int id) {
	  return ManagerHelper.getProjectManager().getProjectByEmployee(id);
	}
	@GET
	@Path("getProjectByCustomer")
	public List<Project> getProjectByCustomer(@QueryParam("id") int id) {
	  return ManagerHelper.getProjectManager().getProjectByCustomer(id);
	}
}
