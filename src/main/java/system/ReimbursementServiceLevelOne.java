package system;

import java.util.List;
import creatable.Employee;
import creatable.NewRequest;
import creatable.ReimbursementDecided;
import creatable.ReimbursementRequest;

public interface ReimbursementServiceLevelOne<E, R> {

	public void createReimbursementRequest(E e, R r);
	public List<ReimbursementRequest> getReimbursementRequests();
	public List<ReimbursementDecided> getResolvedReimbursementRequests(E e);
	
}
