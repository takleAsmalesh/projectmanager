package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/Properties")
public class Properties {

	@GET
	@Path("getHourList")
	public String getHourList() {
		String hours = PropsHelper.get("hours");
		return hours;
	}
   @GET
   @Path("sethours")
   public void setHours(@QueryParam("starttime")String starttime,@QueryParam("endtime")String endtime){
	  PropsHelper.set("hours",starttime+  "," +endtime);
	  
   }
   @GET
   @Path("getDays")
   public String getDays(){
		String days = PropsHelper.get("days");
		return days;
   }
   @GET
   @Path("setDays")
   public void setDays(@QueryParam("days")String days){
	  PropsHelper.set("days",days);
	  System.out.println(days);
   }
   	
}
