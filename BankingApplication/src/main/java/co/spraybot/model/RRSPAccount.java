package co.spraybot.model;

public class RRSPAccount extends Account{
	private String accountTypeName;
	
	public RRSPAccount(int accountId, Customer customer, int accountNum, int balance, int accountType) {
		super(accountId, customer, accountNum, balance, accountType);
		this.accountTypeName = "RRSP Account";
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	
}

