package creatable;

public class NewRequest {

	private String description;
	private double amount;
	
	public NewRequest(String description, String amount) {
		super();
		this.description = description;
		this.amount = Double.parseDouble(amount);
	}

	public NewRequest() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setAmount(String amount) {
		this.amount = Double.parseDouble(amount);
	}

	@Override
	public String toString() {
		return "NewRequest [description=" + description + ", amount=" + amount + "]";
	}
	
	
}
