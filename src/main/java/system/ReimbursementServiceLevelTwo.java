package system;

import java.util.List;

public interface ReimbursementServiceLevelTwo<T> {

	public void judgeReimbursementRequest();
	public List<T> viewReimbursementRequests();
	public List<T> viewJudgedReimbursementRequests();
	public List<T> viewAllEmployees();
	public List<T> viewReimbursementRequestsByEmployee(T t);
	public void registerEmployee();
}
