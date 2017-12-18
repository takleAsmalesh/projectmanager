package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import entity.Customer;
import entity.EmployeeProject;
import entity.Project;
import entity.User;
import manager.ManagerHelper;

@Path("/employeeProject")
public class EmployeeProjectService {
	@GET
	@Path("createEmployeeProject")
	public EmployeeProject createEmployeeProject(@QueryParam("employee")int employee,
			@QueryParam("project")int project){
		return  ManagerHelper.getEmployeeProjectManager().createEmployeeProject(employee,project);
	}
	@GET
	@Path("deleteEmployeeProject")
	public Reply deleteEmployeeProject(@QueryParam("id") int id) {
	  return ManagerHelper.getEmployeeProjectManager().deleteEmployeeProject(id);
	}
	
}