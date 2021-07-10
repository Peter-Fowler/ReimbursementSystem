package general;

import creatable.Employee;
import creatable.ReimbursementRequest;
import database.EmployeeDAOImpl;
import system.ReimbursementServiceImpl;

public class ManualTesting {

	public static void main(String[] args) {
		ReimbursementServiceImpl rsi = new ReimbursementServiceImpl();
		EmployeeDAOImpl edaoi = new EmployeeDAOImpl();
		
		Employee fred = edaoi.get("peter.fowler@revature.net").remove(0);
		
		Employee ceo = edaoi.get("big.boss@revature.net").remove(0);
		
		rsi.createReimbursementRequest(fred, 200.86, "An example to make sure everything works.");
		
		ReimbursementRequest request = rsi.getReimbursementRequests().remove(0);
		
		rsi.judgeReimbursementRequest(request, ceo, true);

	}

}
