package manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.User;
import service.Reply;

public class CustomerManager {

	private final EntityManager entityManager;

	public CustomerManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl)this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 			
	}

	public void update(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.merge(customer);
		entityManager.getTransaction().commit();
	}

	public void create(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
	}

	public void delete(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.remove(customer);
		entityManager.getTransaction().commit();
	}

	public Customer get(Integer id) {
		return entityManager.find(Customer.class, id);
	}

	public List<Customer> getByCompanyname(String companyname) {
		String sql = "select * from customer where companyname like '";
		return (List) entityManager.createNativeQuery(sql + companyname + "'", Customer.class).getResultList();
	}

	public List<Customer> getCustomersByStatus() {
		String sql = "select c.id,c.companyname, true as 'isactive' from customer c "
				+ "where(select count(p.id) from project p " + "where p.customer = c.id and (current_date() between "
				+ "p.startdate and p.enddate) > 0)";
		return (List) entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}

	public List<Customer> getAllCustomers() {
		String sql = "SELECT * FROM projectmanager.customer c;";
		return (List) entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}

	public List<Customer> getActiveAndBigCustomer() {
		String sql = "SELECT c.id, c.companyname, (select count(p.id) from project p"
				+ " where p.customer = c.id and (current_date() between"
				+ " p.startdate and p.enddate)) >=0 as 'isactive',"
				+ " (select count(p.id) from project p where p.customer = c.id) as 'projectcount' from customer c";
		return (List) entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}

	public Customer createCustomer(String companyname, String companynumber, String contactname, String email,
			String phone,String username,String password) {
		try {
			User user = new User(username,password,"customer");  
		    ManagerHelper.getUserManager().create(user);
		    
			Customer customer = new Customer();
			customer.setCompanyname(companyname);
			customer.setCompanynumber(companynumber);
			customer.setContactname(contactname);
			customer.setEmail(email);
			customer.setPhone(phone);
			customer.setUser(user);

			entityManager.getTransaction().begin();
			entityManager.persist(customer);
			entityManager.getTransaction().commit();
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Reply deleteCustomer(int id) {
		try {
			Customer customer = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(customer.getUser());
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}

	}

	public Reply updateCustomer(int id, String companyname, String companynumber, String contactname, String email,
			String phone) {
		try {
			Customer customer = new Customer(id, companyname, companynumber, contactname, email, phone);
			entityManager.getTransaction().begin();
			entityManager.merge(customer);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
}
