package co.spraybot.model;

public class SavingsAccount extends Account{
	private String accountTypeName;
	
	public SavingsAccount(int accountId, Customer customer, int accountNum, int balance, int accountType) {
		super(customer, accountNum, balance, accountType);
		this.accountTypeName = "Savings Account";
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	
}
