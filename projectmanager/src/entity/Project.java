			package entity;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String projectname;
	@ManyToOne
	@JoinColumn(name="customer")
	private Customer customer;
	private String customerprojectmanager;
	private String projectmanageremail;
	private String projectmanagerephone;
	private String startdate;
	private String enddate;

public  Project() {
		
	}
    public  Project(String projectname,Customer customer,String customerprojectmanager,
    		String projectmanageremail,String projectmanagerephone,String startdate,String enddate) {
    	this.projectname=projectname;
    	this.customer=customer;
    	this.customerprojectmanager=customerprojectmanager;
    	this.projectmanageremail=projectmanageremail;
    	this.projectmanagerephone=projectmanagerephone;
    	this.startdate=startdate;
    	this.enddate=enddate;
	}
    public  Project(int id,String projectname,Customer customer,String customerprojectmanager,
    		String projectmanageremail,String projectmanagerephone,String startdate,String enddate) {
    	this.id=id;
    	this.projectname=projectname;
    	this.customer=customer;
    	this.customerprojectmanager=customerprojectmanager;
    	this.projectmanageremail=projectmanageremail;
    	this.projectmanagerephone=projectmanagerephone;
    	this.startdate=startdate;
    	this.enddate=enddate;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomerprojectmanager() {
		return customerprojectmanager;
	}

	public void setCustomerprojectmanager(String customerprojectmanager) {
		this.customerprojectmanager = customerprojectmanager;
	}

	public String getProjectmanageremail() {
		return projectmanageremail;
	}

	public void setProjectmanageremail(String projectmanageremail) {
		this.projectmanageremail = projectmanageremail;
	}

	public String getProjectmanagerephone() {
		return projectmanagerephone;
	}

	public void setProjectmanagerephone(String projectmanagerephone) {
		this.projectmanagerephone = projectmanagerephone;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
    
}
