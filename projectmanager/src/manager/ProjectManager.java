package manager;


import java.util.List;
import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Project;
import service.Reply;

public class ProjectManager {

	private final EntityManager entityManager;

	public ProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl)this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 			
	}

	public void update(Project project) {
		entityManager.getTransaction().begin();
		entityManager.merge(project);
		entityManager.getTransaction().commit();
	}

	public void create(Project project) {
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
	}

	public void delete(Project project) {
		entityManager.getTransaction().begin();
		entityManager.remove(project);
		entityManager.getTransaction().commit();
	}

	public Project get(Integer id) {
		return entityManager.find(Project.class, id);
	}

	public List<Project> getByProjectname(String projectname) {
		String sql = "select * from project where projectname like '";
		return (List) entityManager.createNativeQuery(sql + projectname + "'", Project.class).getResultList();
	}

	public List<Project> getAllProjects() {
		String sql = "SELECT p.id,p.projectname,p.customer,p.customerprojectmanager,p.projectmanageremail,p.projectmanagerephone,"
				+ " DATE_FORMAT(startdate,'%Y-%m-%d')startdate,DATE_FORMAT(enddate,'%Y-%m-%d')enddate  FROM projectmanager.project p ";
		return (List) entityManager.createNativeQuery(sql, Project.class).getResultList();
	}

	public List<Project> getActiveProjects() {
		String sql = "SELECT p.id,p.projectname,p.customer,p.customerprojectmanager,p.projectmanageremail,p.projectmanagerephone,"
				+ " DATE_FORMAT(startdate,'%Y-%m-%d')startdate,DATE_FORMAT(enddate,'%Y-%m-%d')enddate  FROM projectmanager.project p "
				+ " where enddate >= current_date()";
		return (List) entityManager.createNativeQuery(sql, Project.class).getResultList();

	}
	public List<Project> getProjectBeforeEnd(){
		String sql = "SELECT p.id,p.projectname,p.customer,p.customerprojectmanager,p.projectmanageremail,p.projectmanagerephone,"
				+ " DATE_FORMAT(startdate,'%Y-%m-%d')startdate,DATE_FORMAT(enddate,'%Y-%m-%d')enddate  FROM projectmanager.project p"
				+ " where enddate between now() and date_add(now() , interval 90 day)";
		return (List) entityManager.createNativeQuery(sql, Project.class).getResultList();

	}
	public List<Project> getCustomerActiveProjects(int userId) {
		String sql = "select p.id, p.projectname, p.customerprojectmanager,p.projectmanageremail,p.projectmanagerephone,p.startdate,p.enddate FROM projectmanager.project p" 
							+" inner join customer c on c.id=p.customer "
							+" inner join user u on u.id=c.user "
							+" where(current_date() between p.startdate and p.enddate) and u.id ="+userId;
		return (List) entityManager.createNativeQuery(sql, Project.class).getResultList();

	}

	public Project createProject(String projectname, Integer customer, String customerprojectmanager,
			String projectmanageremail, String projectmanagerephone, String startdate, String enddate) {
		try {
			
			Customer customer1 = ManagerHelper.getCustomerManager().get(customer);
			Project project = new Project(projectname, customer1, customerprojectmanager, projectmanageremail,
					projectmanagerephone, startdate, enddate);
			entityManager.getTransaction().begin();
			entityManager.persist(project);
			entityManager.getTransaction().commit();
			return project;
		} catch (Exception e) {
			return null;
		}
	}

	public Reply deleteProject(int id) {
		try {
			Project project = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(project);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	public Reply updateProject(int id, String projectname, int customer, String customerprojectmanager,
			String projectmanageremail, String projectmanagerephone, String startdate, String enddate) {
		
		Customer customer1 = ManagerHelper.getCustomerManager().get(customer);
		Project project = new Project(id,projectname,customer1,customerprojectmanager,projectmanageremail,
				projectmanagerephone,startdate,enddate);
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(project);
			entityManager.getTransaction().commit();
			System.out.println("ArriveTo" +project);
			return new Reply();
		} catch (Exception e) {
			e.printStackTrace();
			Reply r = new Reply();
			r.setId(-1);
			r.setMsg(e.getMessage());
			return r;
		}
	}
	
	public List<Project> getProjectByEmployee(int id){
		String sql = "SELECT p.id,p.projectname,p.customer,p.customerprojectmanager,p.projectmanageremail,p.projectmanagerephone,p.startdate,p.enddate FROM projectmanager.project p"
							+" inner join projectmanager.employeeproject em on em.project=p.id"
							+" inner join projectmanager.employee e on e.id=em.employee"
							+" inner join projectmanager.user u on u.id = e.user" 
							+" and u.id= "+id;
		return (List)entityManager.createNativeQuery(sql, Project.class).getResultList();
	}
	public List<Project> getProjectByCustomer(int id){
		String sql = "SELECT p.id,p.projectname FROM projectmanager.project p"
							+" inner join projectmanager.customer c on c.id=p.customer"
							+" inner join projectmanager.user u on u.id = c.user" 
							+" and u.id= "+id;
		return (List)entityManager.createNativeQuery(sql, Project.class).getResultList();
	}

}
