package general;

import java.util.List;

import creatable.Employee;
import creatable.ReimbursementRequest;
import database.EmployeeDAOImpl;
import system.ReimbursementServiceImpl;

public class ManualTesting {

	public static void main(String[] args) {
		
		System.out.println("Start");
		
		ReimbursementServiceImpl rsi = new ReimbursementServiceImpl();
		EmployeeDAOImpl edaoi = new EmployeeDAOImpl();
		
		Employee fred = edaoi.get("peter.fowler@revature.net").remove(0);
		
		Employee ceo = edaoi.get("big.boss@revature.net").remove(0);
		
		
		  List<ReimbursementRequest> requestList = rsi.getReimbursementRequests();
		  
		  boolean oneEach = false;
		  
		  for(int i = 0; i < 4; i++) {
			  
			  System.out.println(i + " Start of loop");
			  
			  ReimbursementRequest request = requestList.remove(0);
			  
			  if(request.getRequestID() == 1 || request.getRequestID() == 21 || request.getRequestID() == 22) {
				  continue;
			  }
			  
			  rsi.judgeReimbursementRequest(request, ceo, (oneEach ? true:false));
			  
			  oneEach = oneEach ? false : true;
			  
			  System.out.println(i);
		  }
		 System.out.println("End");
	}

}
