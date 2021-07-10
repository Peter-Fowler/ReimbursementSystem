package system;

import java.util.List;
import creatable.Employee;
import creatable.ReimbursementDecided;
import creatable.ReimbursementRequest;

public interface ReimbursementServiceLevelOne {

	public void createReimbursementRequest(Employee fred, double amount, String description);
	public List<ReimbursementRequest> getReimbursementRequests();
	public List<ReimbursementDecided> getResolvedReimbursementRequests(Employee fred);
	
}
