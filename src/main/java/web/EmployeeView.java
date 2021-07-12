package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import creatable.Employee;
import creatable.NewRequest;
import creatable.ReimbursementRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
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
	  
	  System.out.println(requests);
	  
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
		
		System.out.println("In the post");
		
		ObjectMapper om = new ObjectMapper();
		
		HttpSession session = req.getSession(false);
		
		Employee fred = (Employee) session.getAttribute("fred");
		
		String inputLine;
		
		String str = "";
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
			
			System.out.println("in the frist try block");
			
			while((inputLine = reader.readLine()) != null) {
				str += inputLine;
			}
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(str);
		
		try {
		
		NewRequest newRequest = om.readValue(str, NewRequest.class);
		
		System.out.println("in the seconed try block");
		
		rsi.createReimbursementRequest(fred, newRequest);
		
		System.out.println("after putting the new requets in the database");
		
		ReimbursementRequest request = rsi.getNewRequest(fred); 
		
		System.out.println("after getting the new request form the database");
		  
		String listToJson = om.writeValueAsString(request);
		  
		PrintWriter pw = res.getWriter();
		  
		pw.println(listToJson);
		
		System.out.println("after sending the new request to the web");
		  
		}catch(InvalidDefinitionException e) {
			
		e.printStackTrace(); 
		
		}catch(JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
		 
	}
	
}
