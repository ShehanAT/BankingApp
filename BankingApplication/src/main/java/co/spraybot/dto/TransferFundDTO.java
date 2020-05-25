package co.spraybot.dto;

import javax.validation.constraints.NotNull;

public class TransferFundDTO {
	@NotNull
	private int firstAccId;
	@NotNull
	private int secondAccId;
	@NotNull
	private int customerId;
	@NotNull
	private int amount;
	
	public int getFirstAccId() {
		return firstAccId;
	}
	public void setFirstAccId(int firstAccId) {
		this.firstAccId = firstAccId;
	}
	public int getSecondAccId() {
		return secondAccId;
	}
	public void setSecondAccId(int secondAccId) {
		this.secondAccId = secondAccId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
		builder.append("TransferFundDTO [firstAccId=");
		builder.append(firstAccId);
		builder.append(", secondAccId=");
		builder.append(secondAccId);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
