package creatable;

import java.time.LocalDateTime;

public class ReimbursementDecided {

	String managerEmail;
	LocalDateTime date;
	boolean approved;
	int decisionID;
	int requestID;
	
	public ReimbursementDecided(String managerEmail, LocalDateTime date, boolean approved, int decisionID,
			int requestID) {
		super();
		this.managerEmail = managerEmail;
		this.date = date;
		this.approved = approved;
		this.decisionID = decisionID;
		this.requestID = requestID;
	}

	public ReimbursementDecided(String managerEmail, LocalDateTime date, boolean approved, int decisionID) {
		super();
		this.managerEmail = managerEmail;
		this.date = date;
		this.approved = approved;
		this.decisionID = decisionID;
	}

	public ReimbursementDecided() {}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getDecisionID() {
		return decisionID;
	}

	public void setDecisionID(int decisionID) {
		this.decisionID = decisionID;
	}

	public int getRequestID() {
		return requestID;
	}
	
	
}
