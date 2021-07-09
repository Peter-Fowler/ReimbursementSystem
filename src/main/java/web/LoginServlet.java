package web;

import java.io.IOException;
import java.io.PrintWriter;
import creatable.Employee;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import system.ReimbursementServiceImpl;


public class LoginServlet extends HttpServlet {

	private ReimbursementServiceImpl rsi;

	@Override
	public void init() throws ServletException {
		rsi = new ReimbursementServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse
	response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.getRequestDispatcher("/LoginPage.html").forward(request, response);
	  }
	 

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		res.setContentType("text/html");
		
		HttpSession session = req.getSession();
		
		String page = "";
		  if(req.getParameterMap().containsKey("password")) { 
			  String pass = req.getParameter("password"); 
			  String email = req.getParameter("email");
		  
			  Employee fred = rsi.login(email, pass); 
		  
			  if(fred != null) {
				  session.setAttribute("fred", fred);
				  
				  if(fred.isManagerStatus()) {
					  page = "./ManagerPortal";
				  }else {
					  page = "./Reimbursements";
				  }
			  }else {
				  page = "/login";
			  
			  }
		  }
		  res.sendRedirect(page);
	}
}
