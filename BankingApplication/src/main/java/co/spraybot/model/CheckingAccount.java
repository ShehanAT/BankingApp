package co.spraybot.model;

public class CheckingAccount extends Account{
	private String accountTypeName;
	
	public CheckingAccount( Customer customer, int accountNum, int balance, int accountType) {
		super(customer, accountNum, balance, accountType);
		this.accountTypeName = "Checking Account";
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	
}
