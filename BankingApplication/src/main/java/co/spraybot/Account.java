package co.spraybot;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id
	private int accountId;
	@ManyToOne()
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer; //foreign key to Customer.customerId
	private int accountNum;
	private int balance;
	
	public Account(int accountId, Customer customer, int accountNum, int balance) {
		super();
		this.accountId = accountId;
		this.customer = customer;
		this.accountNum = accountNum;
		this.balance = balance;
	}
	
	public Account() {
		
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", customerId=" + customer.toString() + ", accountNum=" + accountNum
				+ ", balance=" + balance + "]";
	}
	
}
