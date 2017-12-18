package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PropsHelper;

@WebServlet("/Props")
public class PropsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PropsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hours = PropsHelper.get("Hours");
		PropsHelper.set("hours", "08-00,19-00");
		String days = PropsHelper.get("days");
		PropsHelper.set("days", "Sunday, Monday, Tuesday, Wednesday ,Thursday ,Friday ,Saturday");
		
	}

}
