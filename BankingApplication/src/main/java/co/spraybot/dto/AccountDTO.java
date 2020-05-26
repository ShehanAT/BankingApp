package co.spraybot.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountDTO {
	@NotNull
	private int accountNumber;
	
	@NotNull
	private int accountType;
	
	@NotNull
	private int balance;
	
	@NotNull
	private int customerId;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountDTO [accountNumber=");
		builder.append(accountNumber);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", balance=");
		builder.append(balance);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
