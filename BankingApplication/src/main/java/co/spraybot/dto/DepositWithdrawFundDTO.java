package co.spraybot.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DepositWithdrawFundDTO {
	@NotNull
	private int customerId;
	@NotNull
	private int accountId;
	@NotNull
	private int amount;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DepositFundDTO [customerId=");
		builder.append(customerId);
		builder.append(", accountId=");
		builder.append(accountId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}
	
	
}
