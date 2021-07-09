package web;

import java.io.IOException;
import java.util.List;

import creatable.Employee;
import creatable.ReimbursementRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import system.ReimbursementServiceImpl;

public class EmployeeView extends HttpServlet {
	
	private ReimbursementServiceImpl rsi;
	
	@Override
	public void init() throws ServletException{
		rsi = new ReimbursementServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		HttpSession session = req.getSession(false);
		
		req.getRequestDispatcher("/RemibusementRequestViewEmployee.html").forward(req, res);
		
	
	  Employee fred = (Employee) session.getAttribute("fred"); 

	  List<ReimbursementRequest> requests = rsi.viewReimbursementRequestsByEmployee(fred);
	  
	  for(ReimbursementRequest r : requests) {
		  System.out.println(r);
	  }
	 
	}
}
