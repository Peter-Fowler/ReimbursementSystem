package creatable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReimbursementRequest {

	String employeeEmail;
	String date;
	double amount;
	String description;
	int requestID;
	
	public ReimbursementRequest(String employeeEmail, String date, double amount, String description,
			int requestID) {
		super();
		this.employeeEmail = employeeEmail;
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.requestID = requestID;
	}

	public ReimbursementRequest(String employeeEmail, String date, double amount, String description) {
		super();
		this.employeeEmail = employeeEmail;
		this.date = date;
		this.amount = amount;
		this.description = description;
	}
	
	public ReimbursementRequest() {}
	
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public int getRequestID() {
		return requestID;
	}

	@Override
	public String toString() {
		return "ReimbursementRequest [employeeEmail=" + employeeEmail + ", date=" + date + ", amount=" + amount
				+ ", description=" + description + ", requestID=" + requestID + "]";
	}
	
}
