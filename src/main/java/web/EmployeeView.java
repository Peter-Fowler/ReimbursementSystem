package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import creatable.Employee;
import creatable.ReimbursementRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import system.ReimbursementServiceImpl;

public class EmployeeView extends HttpServlet {
	
	private ReimbursementServiceImpl rsi;
	
	@Override
	public void init() throws ServletException{
		rsi = new ReimbursementServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setStatus(200);
		res.setContentType("application/json");
		
		HttpSession session = req.getSession(false);
		
		ObjectMapper om = new ObjectMapper();
	
	  Employee fred = (Employee) session.getAttribute("fred"); 

	  List<ReimbursementRequest> requests = rsi.viewReimbursementRequestsByEmployee(fred);
	  try {
	  	String listToJson = om.writeValueAsString(requests);
	  	
	  	PrintWriter pw = res.getWriter();
		  
	  	pw.println(listToJson);
		 
	  }catch(InvalidDefinitionException e) {
		  e.printStackTrace();
	  }
	  
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		
		Employee fred = (Employee) session.getAttribute("fred");
		
		if(fred != null) {
			String amount = req.getParameter("amount");
		}
	}
	
}
