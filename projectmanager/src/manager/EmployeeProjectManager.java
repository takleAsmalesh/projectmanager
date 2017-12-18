package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Employee;
import entity.EmployeeProject;
import entity.Project;
import service.Reply;

public class EmployeeProjectManager {

	private final EntityManager entityManager;

	public EmployeeProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl)this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 			
	}

	public void update(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.merge(employeeProject);
		entityManager.getTransaction().commit();
	}

	public void create(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.persist(employeeProject);
		entityManager.getTransaction().commit();
	}

	public void delete(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.remove(employeeProject);
		entityManager.getTransaction().commit();
	}

	private EmployeeProject get(Integer id) {
		return entityManager.find(EmployeeProject.class, id);
	}

	public EmployeeProject createEmployeeProject(int employee, int project) {
		Employee em = ManagerHelper.getEmployeeManager().get(employee);
		Project p = ManagerHelper.getProjectManager().get(project);
		
		try {
			EmployeeProject employeeProject = new EmployeeProject(em,p);
			entityManager.getTransaction().begin();
			entityManager.persist(employeeProject);
			entityManager.getTransaction().commit();
			return employeeProject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Reply deleteEmployeeProject(int id) {
		try {
			EmployeeProject employeeProject = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(employeeProject);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	

}
