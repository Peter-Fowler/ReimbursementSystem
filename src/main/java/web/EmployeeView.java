package web;

import java.io.IOException;
import java.util.List;
import creatable.ReimbursementRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	//	ObjectMapper om = new ObjectMapper();
		
		res.setContentType("text/html");
		req.getRequestDispatcher("/RemibusementRequestViewEmployee.html").forward(req, res);
		
	/*
	 * if(req.getParameterMap().containsKey("email")) { String email =
	 * req.getParameter("email"); List<ReimbursementRequest> requests =
	 * rsi.viewReimbursementRequestsByEmployee(fred);
	 * 
	 * }
	 */
			
		
	}
}
