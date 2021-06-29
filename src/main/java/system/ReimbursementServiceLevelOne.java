package system;

import java.util.List;
import creatable.Employee;

public interface ReimbursementServiceLevelOne<T> {

	public void submitReimbursementRequest();
	public List<T> rendingReimbursementRequests();
	public List<T> resolvedReimbursementRequests();
	
}
