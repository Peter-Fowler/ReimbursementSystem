package creatable;

public class Employee {

	private String firstName;
	private String lastName;
	private String email;
	private String jobTitle;
	private String manager;
	private boolean managerStatus;
	private String password;
	
	

	public Employee(String firstName, String lastName, String email, String jobTitle, 
			String manager, boolean managerStatus, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.jobTitle = jobTitle;
		this.manager = manager;
		this.managerStatus = managerStatus;
		this.password = password;
	}

	public Employee(String firstName, String lastName, String email, String jobTitle, String manager, boolean managerStatus) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.jobTitle = jobTitle;
		this.manager = manager;
		this.managerStatus = managerStatus;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getEmail() {
		return email;
	}

	public boolean isManagerStatus() {
		return managerStatus;
	}

	public void setManagerStatus(boolean managerStatus) {
		this.managerStatus = managerStatus;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int booleanToInt(boolean tf) {
		return (tf) ? 1:0;
	}
	
	public boolean intToBoolean(int tf) {
		return (tf == 1) ? true:false;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", jobTitle="
				+ jobTitle + ", manager=" + manager + ", managerStatus=" + managerStatus + "]";
	}
	
	
}
