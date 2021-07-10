package system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import creatable.Employee;
import creatable.ReimbursementDecided;
import creatable.ReimbursementRequest;
import database.EmployeeDAOImpl;
import database.ReimbursementDecidedDAOImpl;
import database.ReimbursementRequestDAOImpl;

public class ReimbursementServiceImpl implements ReimbursementServiceLevelOne,  
ReimbursementServiceLevelTwo<ReimbursementRequest, ReimbursementDecided, Employee>{

	ReimbursementRequestDAOImpl rrdaoi = new ReimbursementRequestDAOImpl();
	
	ReimbursementDecidedDAOImpl rddaoi = new ReimbursementDecidedDAOImpl();
	
	EmployeeDAOImpl edaoi = new EmployeeDAOImpl();
	
	
	
	@Override // In testing mode
	public void createReimbursementRequest(Employee fred, double amount, String description) {
		
		String date = catchTime();
		
		ReimbursementRequest request = new ReimbursementRequest(fred.getEmail(), date, amount, description);
		
		rrdaoi.save(request);
	}

	@Override
	public ArrayList<ReimbursementRequest> getReimbursementRequests() {
				
		return rrdaoi.getAll();
	}

	@Override
	public List<ReimbursementDecided> getResolvedReimbursementRequests(Employee fred) {

		return rddaoi.get(fred.getEmail());
	}

	@Override
	public void judgeReimbursementRequest(ReimbursementRequest request, Employee bigFred, boolean tf) {
		
		String date =  catchTime();
		
		ReimbursementDecided decided = new ReimbursementDecided(bigFred.getEmail(), date,
				tf, request.getRequestID());
		
		rddaoi.save(decided);
		
	}

	@Override
	public ArrayList<ReimbursementDecided> viewReimbursementDecided() {

		return rddaoi.getAll();
	}

	@Override
	public ArrayList<Employee> viewAllEmployees() {
		
		return edaoi.getAll();
	}

	@Override
	public ArrayList<ReimbursementRequest> viewReimbursementRequestsByEmployee(Employee fred) {
		
		return rrdaoi.get(fred.getEmail());
	}

	@Override
	public void registerEmployee(Employee fred) {

		edaoi.save(fred);
		
	}

	public Employee login(String email, String pass) {
		if(edaoi.validate(email, pass)) {
			List<Employee> emp = edaoi.get(email);
			Employee fred = emp.remove(0);
			return fred;
		}
		return null;
	}

	public static String catchTime() {
		LocalDateTime date = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
		
		String now = date.format(formatter);
				
		return now;
	}

}
