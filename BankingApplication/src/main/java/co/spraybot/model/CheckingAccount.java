package co.spraybot.model;

public class CheckingAccount extends Account{
	private String accountTypeName;
	
	public CheckingAccount(int accountId, Customer customer, int accountNum, int balance, int accountType) {
		super(accountId, customer, accountNum, balance, accountType);
		this.accountTypeName = "Checking Account";
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	
}
