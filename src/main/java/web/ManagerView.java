package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import creatable.Employee;
import creatable.NewDesision;
import creatable.NewRequest;
import creatable.ReimbursementRequest;
import system.ReimbursementServiceImpl;

public class ManagerView extends HttpServlet{

	private ReimbursementServiceImpl rsi;
	
	@Override
	public void init() throws ServletException {
		rsi = new ReimbursementServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse
	res) throws ServletException, IOException {
		
		res.setContentType("application/json");
		
		ObjectMapper om = new ObjectMapper();
		
		HttpSession session = req.getSession(false);
		
		Employee bigFred = (Employee) session.getAttribute("fred");
		
		List<ReimbursementRequest> request = rsi.getReimbursementRequests();
		
		System.out.println(request);
		
		try {
		  	String listToJson = om.writeValueAsString(request);
		  	
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
		
		Employee bigFred = (Employee) session.getAttribute("fred");
		
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
		
		/*
		 * try {
		 * 
		 * NewDesision newDesision = om.readValue(str, NewRequest.class);
		 * 
		 * System.out.println("in the seconed try block");
		 * 
		 * rsi.judgeReimbursementRequest(bigFred, newDesision);
		 * 
		 * System.out.println("after putting the new requets in the database");
		 * 
		 * ReimbursementRequest request = rsi.getNewRequest(bigFred);
		 * 
		 * System.out.println("after getting the new request form the database");
		 * 
		 * String listToJson = om.writeValueAsString(request);
		 * 
		 * PrintWriter pw = res.getWriter();
		 * 
		 * pw.println(listToJson);
		 * 
		 * System.out.println("after sending the new request to the web");
		 * 
		 * }catch(InvalidDefinitionException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * }catch(JsonProcessingException e) {
		 * 
		 * e.printStackTrace(); }
		 */
		
		 
	}
	
}
