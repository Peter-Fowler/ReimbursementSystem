package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import creatable.Employee;
import creatable.ReimbursementDecided;
import system.ReimbursementServiceImpl;

public class EmployeeDecidedView extends HttpServlet {
	
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
		
		req.getRequestDispatcher("/RemibusementRequestViewEmployee.html").forward(req, res);
		
		ObjectMapper om = new ObjectMapper();
	
	  Employee fred = (Employee) session.getAttribute("fred"); 

	  List<ReimbursementDecided> decided = rsi.getResolvedReimbursementRequests(fred);
	  try {
		  String listToJson = om.writeValueAsString(decided);
		  
		  PrintWriter pw = res.getWriter();
		  
		  pw.println(listToJson);
		  
	  }catch(JsonProcessingException e) {
		  e.printStackTrace();
	  }catch(IOException e) {
		  e.printStackTrace();
	  }
	  
	  for(ReimbursementDecided r : decided) {
		  System.out.println(r);
	  }
	  
	}

}
