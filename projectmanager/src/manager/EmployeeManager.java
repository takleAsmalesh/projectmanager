package manager;

import java.util.List;
import javax.persistence.EntityManager;
import org.apache.openjpa.persistence.EntityManagerImpl;
import entity.Employee;
import entity.User;
import service.Reply;

public class EmployeeManager {

	private final EntityManager entityManager;

	public EmployeeManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl)this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 			
	}

	public void update(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.merge(employee);
		entityManager.getTransaction().commit();
	}

	public void create(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
	}

	public void delete(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.remove(employee);
		entityManager.getTransaction().commit();
	}

	public Employee get(Integer id) {
		return entityManager.find(Employee.class, id);
	}

	public List<Employee> getByName(String firstname) {
		String sql = "select * from employee where firstname like '";
		return (List) entityManager.createNativeQuery(sql + firstname + "'", Employee.class).getResultList();
	}

	public List<Employee> getAll() {
		String sql = "SELECT id, firstname,lastname, email, phone,user FROM projectmanager.employee;";
		return  entityManager.createNativeQuery(sql, Employee.class).getResultList();
	}

	public Employee createEmployee(String firstname, String lastname, String email, String phone, String username,String password) {
		try {
		User user = new User(username,password,"employee");  
	    ManagerHelper.getUserManager().create(user);
			Employee employee = new Employee();
			employee.setFirstname(firstname);
			employee.setLastname(lastname);
			employee.setEmail(email);
			employee.setPhone(phone);
			employee.setUser(user);
			
			entityManager.getTransaction().begin();
			entityManager.persist(employee);
			entityManager.getTransaction().commit();
			return employee;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Reply deleteEmployee(int id) {
		Employee employee = get(id);
		
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(employee.getUser());
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}

	}

	public Reply updateEmployee(int id, String firstname, String lastname, String email, String phone) {
		System.out.println(id+","+firstname+","+email+","+phone);
		try {
			Employee employee = get(id);
			employee.setFirstname(firstname);
			employee.setLastname(lastname);
			employee.setEmail(email);
			employee.setPhone(phone);
			
			entityManager.getTransaction().begin();
			entityManager.merge(employee);
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