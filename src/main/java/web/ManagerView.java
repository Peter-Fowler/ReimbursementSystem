package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import system.ReimbursementServiceImpl;

public class ManagerView extends HttpServlet{

	private ReimbursementServiceImpl rsi;
	
	@Override
	public void init() throws ServletException {
		rsi = new ReimbursementServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse
	response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		request.getRequestDispatcher("/ManagerHome.html").forward(request, response);
	}
	
	
}
