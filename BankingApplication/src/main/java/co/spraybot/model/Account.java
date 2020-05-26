package co.spraybot.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@ManyToOne()
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer; //foreign key to Customer.customerId
	private int accountNum;
	private int balance;
	@Column(nullable=true)
	private int accountType; //4 types of accounts: (0)-checking, (1)-dividend, (2)-savings, (3)-RRSP
	
	public Account(Customer customer, int accountNum, int balance, int accountType) {
		super();
		this.customer = customer;
		this.accountNum = accountNum;
		this.balance = balance;
		this.accountType = accountType;
	}

	public Account() {
		
	}
	public int getAccountId() {
		return id;
	}
	public void setAccountId(int id) {
		this.id = id;
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
	
	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", customerId=" + customer.toString() + ", accountNum=" + accountNum
				+ ", balance=" + balance + "]";
	}
	
}
