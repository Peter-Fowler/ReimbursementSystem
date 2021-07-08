package system;

import java.util.ArrayList;

public interface ReimbursementServiceLevelTwo<T, X, E> {

	public void judgeReimbursementRequest(T t, E e, boolean tf);
	public ArrayList<X> viewReimbursementDecided();
	public ArrayList<E> viewAllEmployees();
	public ArrayList<T> viewReimbursementRequestsByEmployee(E e);
	public void registerEmployee(E e);
}

