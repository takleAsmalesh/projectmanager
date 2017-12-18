package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import entity.Customer;
import entity.Employee;
import entity.User;
import manager.ManagerHelper;

@Path("/customer")
public class CustomerService {

	@GET
	@Path("get")
	public Customer getCustomer(@QueryParam("id") int id) {
		return ManagerHelper.getCustomerManager().get(id);
	}

	@GET
	@Path("getByName")
	public List<Customer> getByCompanyname(@QueryParam("companyname") String companyname) {
		return ManagerHelper.getCustomerManager().getByCompanyname(companyname);
	}

	@GET
	@Path("getByStatus")
	public List<Customer> getCustomersByStatus(@QueryParam("active") boolean active) {
			return ManagerHelper.getCustomerManager().getCustomersByStatus();		
	}
	@GET
	@Path("getAllCustomer")
	public List<Customer> getAllCustomers(){
	return  ManagerHelper.getCustomerManager().getAllCustomers();
	}
	@GET
	@Path("getActiveAndBigCustomer")
	public List<Customer> getActiveAndBigCustomer() {
		return  ManagerHelper.getCustomerManager(). getActiveAndBigCustomer();
	}
	@GET
	@Path("deleteCustomer")
	public Reply deleteCustomer(@QueryParam("id") int id) {
		return  ManagerHelper.getCustomerManager().deleteCustomer(id);
	}
	@GET
	@Path("updateCustomer")
	public Reply updateCustomer(@QueryParam("id") int id,
			@QueryParam("companyname") String companyname,
			@QueryParam("companynumber") String companynumber,
			@QueryParam("contactname") String contactname,
			@QueryParam("email") String email,
			@QueryParam("phone") String phone) {
		return  ManagerHelper.getCustomerManager().updateCustomer(id, companyname, companynumber,contactname, email, phone);
	}
	@GET
	@Path("createCustomer")
	public Customer createCustomer(@QueryParam("companyname") String companyname,
			@QueryParam("companynumber") String companynumber,@QueryParam("contactname") String contactname,
			@QueryParam("email") String email,
			@QueryParam("phone") String phone,
			@QueryParam("username") String username,
			@QueryParam("password") String password) {
		return  (Customer)ManagerHelper.getCustomerManager().createCustomer(companyname, companynumber,contactname, email, phone,username,password);
	}
	
}
