package manager;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.MailHelper;
import entity.Project;
import entity.User;
import service.Reply;

public class UserManager {

	private final EntityManager entityManager;

	public UserManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl)this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 			
	}

	public void update(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	public User create(User user) {
		try{
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		
		return user;
	}catch(Exception e){
		return null;
	}
		
	}

	public void delete(User user) {
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}

	public User get(Integer id) {
		return entityManager.find(User.class, id);
	}

	public User getUser(String username, String password) {
		String user = "select * from user where username like '";
		String pass = " and password  like '";
		try {
			return (User) entityManager.createNativeQuery(user + username + "'" + pass + password + "'", User.class)
					.getSingleResult();
		} catch (Exception e) {
			System.out.println("its null"); 
			return null;
		}

	}
	public User createUser(String username , String password,	String type) {
		try {
			User user = new User(username,password,type);
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	public Reply deleteUser(int id ){
		System.out.println("----->"+id);
		try{
		User user = get(id);
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
		return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(-1);
			e.printStackTrace();
			r.setMsg(e.getMessage());
			return r;
		}
	}
	public Reply forgotPassword(String username) {
		
		String sql ="select * from projectmanager.user  where username = '"+username+"' ";
		User user2 = (User)entityManager.createNativeQuery(sql,User.class).getSingleResult();
		
				
		if(user2.getType().equals("employee")){
			
			String employeeForgot = "select * from projectmanager.employee e inner join user u on e.user=u.id " + "where u.username ='"+username+"' ";
			
			Employee employee = (Employee) entityManager.createNativeQuery(employeeForgot,Employee.class).getSingleResult();
			
			try{
				MailHelper.sendMail(employee.getEmail(),"Password To Hourreport Site",
				"User Name : " + user2.getUsername() + " , " + " Password :" +user2.getPassword());
				
			}catch (MessagingException e){
				e.getMessage();
			}
			}else if (user2.getType().equals("customer")) {
				
				String customerForgot = "select * from projectmanager.customer c inner join user u on c.user=u.id " + "where u.username ='"+username+"' ";
				
				Customer customer = (Customer) entityManager.createNativeQuery(customerForgot,Customer.class).getSingleResult();
			
			try{
				
				MailHelper.sendMail(customer.getEmail(), ("Password To Hourreport Site"),
				"User Name : " + user2.getUsername() + " , " + " Password :" +user2.getPassword());
			}catch (MessagingException e){
				e.getMessage();
			}
			}else{
				try{
					MailHelper.sendMail("takleasmalesh@gmail.com","Password To takle Site",
					user2.getUsername() + " , " + user2.getPassword());
				}catch (MessagingException e){
					e.getMessage();
				}
			}
					
		return new Reply();
			
	
	
}
}
