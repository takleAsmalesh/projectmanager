package manager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.HourReport;
import entity.Project;

public class HourReportManager {

	private final EntityManager entityManager;

	public HourReportManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl)this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 			
	}

	public void update(HourReport hourreport) {
		entityManager.getTransaction().begin();
		entityManager.merge(hourreport);
		entityManager.getTransaction().commit();
	}

	public void create(HourReport hourreport) {
		entityManager.getTransaction().begin();
		entityManager.persist(hourreport);
		entityManager.getTransaction().commit();
	}

	public void delete(HourReport hourreport) {
		entityManager.getTransaction().begin();
		entityManager.remove(hourreport);
		entityManager.getTransaction().commit();
	}

	public HourReport get(Integer employee) {
		return entityManager.find(HourReport.class, employee);
	}

	public List<HourReport> getByDescription(String description) {
		String sql = "select * from hourreport where description like '";
		return (List) entityManager.createNativeQuery(sql + description + "'", HourReport.class).getResultList();
	}

	public HourReport createHourReport(int userId, int project, String date, String starthour,String endhour, String description) {
	    
	     String sql = "select * from employee e where e.user="+userId;
		
	 	Employee em = (Employee) entityManager.createNativeQuery(sql, Employee.class).getSingleResult();
	    Project p =ManagerHelper.getProjectManager().get(project);
		try {
			HourReport hourreport = new HourReport(em, p,date, starthour, endhour, description);
			 System.out.println(hourreport);
			entityManager.getTransaction().begin();
			entityManager.persist(hourreport);
			entityManager.getTransaction().commit();
		    System.out.println(hourreport);
			return hourreport;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<HourReport> getAllHourReports() {
		String sql = "SELECT * FROM projectmanager.hourreport";
		return (List) entityManager.createNativeQuery(sql, HourReport.class).getResultList();

	}
	public List<HourReport> getHourReportsByYearAndMonth(String yearAndMonth, int employee, int project, int customer) {
	
		String sql = "SELECT h.id, e.firstname,p.projectname,h.date, h.starthour, h.endhour, h.description from hourreport h "
				+ " inner join employee e on e.id = h.employee "
				+ " inner join project p on p.id = h.project "
				+ " inner join customer c on c.id = p.customer "
				+ " inner join user u on u.id=e.user "
				+ "	 where month(h.date)= month('"+yearAndMonth+"') "
				+ "	 and year(h.date) = year('"+yearAndMonth+"') ";
			
				if(employee != 0){
					sql += " and h.employee = " + employee;
				}
				
				if( project != 0){
					sql +=" and  p.id =  " + project;
				}
				
				 if(customer != 0){
					sql += " and c.id =  " + customer;
				}
		
				sql += " order by h.date desc";
				
							
		return (List)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}
	public List<HourReport> getEmployeeReportsByYearAndMonth(int userId,String yearAndMonth, int project) {
		
		String sql = "SELECT h.id, e.firstname,p.projectname,h.date, h.starthour, h.endhour, h.description from hourreport h "
				+ " inner join employee e on e.id = h.employee "
				+ " inner join project p on p.id = h.project "
				+ " inner join customer c on c.id = p.customer "
				+ " inner join user u on u.id=e.user "
				+ " where month(h.date)= month('"+yearAndMonth+"') "
				+ " and year(h.date) = year('"+yearAndMonth+"') "
				+ " and e.user="+userId; 			
		   
				if( project != 0){
					sql +=" and  p.id =" + project;
				}
				sql += " order by h.date desc";
				
							
		return (List)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}
public List<HourReport> getCustomerReportsByYearAndMonth(int userId,String yearAndMonth, int project) {
		
		String sql = "SELECT h.id, e.firstname,p.projectname,h.date, h.starthour, h.endhour, h.description from hourreport h"
				+ " inner join employee e on e.id = h.employee"
				+ " inner join project p on p.id = h.project"
				+ " inner join customer c on c.id = p.customer"
				+ " inner join user u on u.id=c.user"
				+ " where month(h.date)= month('"+yearAndMonth+"')"
				+ " and year(h.date) = year('"+yearAndMonth+"')"
		     	+ " and c.user="+userId; 
			
				if( project != 0){
					sql +=" and  p.id =  " + project;
				}
				sql += " order by h.date desc";
				
							
		return (List)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}
	
	public List<HourReport> getLastWeekHourReport(int id) {
		System.out.println("id"+id);
		String sql = "SELECT * FROM projectmanager.hourreport h "
						+ " inner join employee e on e.id = h.employee"
						+ " inner join project p on p.id = h.project"
						+ " inner join user u on u.id = e.user"
						+ " where  h.date >= now()- interval 7 day and h.date <= current_date()"
						+ " and u.id = "+id; 
		
			return (List)entityManager.createNativeQuery(sql,HourReport.class).getResultList();
	}
   public List<HourReport> getHourReportByEmployee(int employee){
	   String sql = "SELECT * FROM projectmanager.hourreport h"
	   		+ " inner join employee e on e.id = h.employee"
	   		+ " inner join user u on u.id = e.user"
	   		+ " where u.id="+employee;
	   return  (List) entityManager.createNativeQuery(sql, HourReport.class).getResultList();
   }
   public List<HourReport> getHourReportByCustomer(int customer){
	   String sql = "SELECT * FROM projectmanager.hourreport h inner join project p on p.id =  h.project where customer = '";
	   return  (List) entityManager.createNativeQuery(sql+customer+"'", HourReport.class).getResultList();
   }
}
