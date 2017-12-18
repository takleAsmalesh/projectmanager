package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import entity.Customer;
import entity.User;
import manager.ManagerHelper;

@Path("/user")
public class UserService {

	@GET
	@Path("get")
	public User getUser(@QueryParam("id") int id) {
		
		return ManagerHelper.getUserManager().get(id);
	}
	@GET
	@Path("getuser")
	public User getUser(@QueryParam("username") String username,
			@QueryParam("password") String password) {
		  if(username!=null && password!=null ){
			  return ManagerHelper.getUserManager().getUser(username, password);	
			}else{
				return null;
			}
		
	}
	@GET
	@Path("createUser")
	public User createUser(@QueryParam("username")String username,
			@QueryParam("password") String password,@QueryParam("type") String type){
		 return ManagerHelper.getUserManager().createUser(username, password, type);	
	}
	@GET
	@Path("deleteUser")
	public Reply deleteUser(@QueryParam("id")int id){
		 return ManagerHelper.getUserManager().deleteUser(id);	
	}
	@GET
	@Path("forgetPassword")
	public Reply forgotPassword(@QueryParam("username")String username){
		 return ManagerHelper.getUserManager().forgotPassword(username);	
	}
}
