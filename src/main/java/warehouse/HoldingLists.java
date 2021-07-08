package warehouse;

import java.util.ArrayList;

import creatable.Employee;
import creatable.ReimbursementDecided;
import creatable.ReimbursementRequest;
import database.EmployeeDAOImpl;
import database.ReimbursementDecidedDAOImpl;
import database.ReimbursementRequestDAOImpl;

public class HoldingLists {
	
	private static HoldingLists instance;
	
	private HoldingLists() {}
	
	public static HoldingLists getInstance() {
		if (instance == null) {
			instance = new HoldingLists();
			instance.loadData();
		}
		return instance;
	}

	private ReimbursementRequestDAOImpl requestImpl = new ReimbursementRequestDAOImpl();
	private ReimbursementDecidedDAOImpl decidedImpl = new ReimbursementDecidedDAOImpl();
	private EmployeeDAOImpl employeeImpl = new EmployeeDAOImpl();
	public static ArrayList<Employee> allEmployee;
	public static ArrayList<ReimbursementRequest> allRequests;
	public static ArrayList<ReimbursementDecided> allDecided;
	
	void loadData(){
		allEmployee = employeeImpl.getAll();
		allRequests = requestImpl.getAll();
		allDecided = decidedImpl.getAll();
	}

	public static ArrayList<Employee> getAllEmployee() {
		return allEmployee;
	}

	public static ArrayList<ReimbursementRequest> getAllRequests() {
		return allRequests;
	}

	public static ArrayList<ReimbursementDecided> getAllDecided() {
		return allDecided;
	}
	
	
}
