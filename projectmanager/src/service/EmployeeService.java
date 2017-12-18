package service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Employee;
import manager.ManagerHelper;



@Path("/employee")
public class EmployeeService {

	
	@GET
	@Path("/get")
	public Employee getEmployee(@QueryParam("id") int id) {
		return ManagerHelper.getEmployeeManager().get(id);
	}

	@GET
	@Path("/getByName")
	public List<Employee> getByName(@QueryParam("firstname") String firstname) {
		return ManagerHelper.getEmployeeManager().getByName(firstname);
	}
	@GET
	@Path("/getAll")
	public List<Employee> getAll() {
		return ManagerHelper.getEmployeeManager().getAll();
	}
	
	@GET
	@Path("/createEmployee")
	public Employee addEmployee(@QueryParam("firstname")String firstname,
			@QueryParam("lastname")String lastname,@QueryParam("email")String email,
			@QueryParam("phone")String phone,
			@QueryParam("username")String username,@QueryParam("password")String password) {
		return ManagerHelper.getEmployeeManager().createEmployee( firstname, lastname, email, phone,username,password);
	}
	@GET
	@Path("deleteEmployee")
	public Reply deleteEmployee(@QueryParam("id")int id){
		return  ManagerHelper.getEmployeeManager().deleteEmployee(id);
	}
	@GET
	@Path("updateEmployee")
	public Reply updateEmployee(@QueryParam("id")int id,
			@QueryParam("firstname")String firstname,
			@QueryParam("lastname")String lastname,
			@QueryParam("email")String email,
			@QueryParam("phone")String phone) {
	return (Reply)ManagerHelper.getEmployeeManager().updateEmployee(id, firstname, lastname, email, phone);
	}
	
	
}
