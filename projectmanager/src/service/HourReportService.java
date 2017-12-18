package service;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import entity.Customer;
import entity.HourReport;
import manager.ManagerHelper;

@Path("/hourreport")
public class HourReportService {

	@GET
	@Path("get")
	public HourReport getHourReport(@QueryParam("employeeid") int employeeid) {
		return ManagerHelper.getHourReportManager().get(employeeid);
	}

	@GET
	@Path("getByName")
	public List<HourReport> getByDescription(@QueryParam("description") String description) {
		return ManagerHelper.getHourReportManager().getByDescription(description);
	}

	@GET
	@Path("create")
	public HourReport creatHourReport(@QueryParam("employee") int employee, @QueryParam("project") int project,
			@QueryParam("date") String date,@QueryParam("starthour") String starthour, @QueryParam("endhour") String endhour,
			@QueryParam("description") String description) {
		return ManagerHelper.getHourReportManager().createHourReport(employee, project, date, starthour,endhour, description);
	}

	@GET
	@Path("getAllHourreport")
	public List<HourReport> getAllHourReports() {
		return (List<HourReport>) ManagerHelper.getHourReportManager().getAllHourReports();
	}

	@GET
	@Path("getHourReportsByYearAndMonth")
	public List<HourReport> getHourReportsByYearAndMonth(@QueryParam ("yearAndMonth")String yearAndMonth,
			@QueryParam("employee")int employee,@QueryParam("project")int project,
			@QueryParam("customer")int customer) {
		return ManagerHelper.getHourReportManager().getHourReportsByYearAndMonth(yearAndMonth,employee,project,customer);
	}

	@GET
	@Path("getLastWeekReport")
	public List<HourReport> getLastWeekHourReport(@QueryParam ("id")int id) {
		return  ManagerHelper.getHourReportManager().getLastWeekHourReport(id);
	}

	@GET
	@Path("getHourReportByEmployee")
	public List<HourReport> getHourReportByEmployee(@QueryParam("employee") int employee) {
		return  ManagerHelper.getHourReportManager().getHourReportByEmployee(employee);
	}

	@GET
	@Path("getHourReportByCustomer")
	public List<HourReport> getHourReportByCustomer(@QueryParam("customer") int customer) {
		return  ManagerHelper.getHourReportManager().getHourReportByCustomer(customer);
	}
	@GET
	@Path("getEmployeeReportsByYearAndMonth")
	public List<HourReport> getEmployeeReportsByYearAndMonth(@QueryParam("userId") int userId,
			@QueryParam("yearAndMonth") String yearAndMonth,
			@QueryParam("project") int project) {
		return  ManagerHelper.getHourReportManager().getEmployeeReportsByYearAndMonth(userId,yearAndMonth,project);
	}
	@GET
	@Path("getCustomerReportsByYearAndMonth")
	public List<HourReport> getCustomerReportsByYearAndMonth(@QueryParam("userId") int userId,
			@QueryParam("yearAndMonth") String yearAndMonth,
			@QueryParam("project") int project) {
		return  ManagerHelper.getHourReportManager().getCustomerReportsByYearAndMonth(userId,yearAndMonth,project);
	}
}
