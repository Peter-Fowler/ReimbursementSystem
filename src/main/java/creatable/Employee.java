package creatable;

public class Employee {

	private String name;
	private String email;
	private String jobTitle;
	private String manager;
	
	public Employee(String name, String email, String jobTitle, String manager) {
		super();
		this.name = name;
		this.email = email;
		this.jobTitle = jobTitle;
		this.manager = manager;
	}

	public Employee(String name, String email, String jobTitle) {
		super();
		this.name = name;
		this.email = email;
		this.jobTitle = jobTitle;
	}

	public Employee() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Employee [name=" + name + ", email=" + email + ", jobTitle=" + jobTitle + ", manager=" + manager + "]";
	}
	
}
