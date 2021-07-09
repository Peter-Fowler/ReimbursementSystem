package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import creatable.Employee;
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
		
		res.setContentType("text/html");
		
		HttpSession session = req.getSession(false);
		
		Employee bigFred = (Employee) session.getAttribute("fred");
		
		req.getRequestDispatcher("/ManagerHome.html").forward(req, res);
		
		List<ReimbursementRequest> request = rsi.getReimbursementRequests();
		
		System.out.println(request);
	}
	
	
}
