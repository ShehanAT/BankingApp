package co.spraybot.model;

public class DividendAccount extends Account{
	private String accountTypeName;
	
	public DividendAccount(int accountId, Customer customer, int accountNum, int balance, int accountType) {
		super(customer, accountNum, balance, accountType);
		this.accountTypeName = "Dividend Account";
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	
}
