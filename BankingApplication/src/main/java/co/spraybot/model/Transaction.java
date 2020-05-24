package co.spraybot.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="account_id", unique=true)
	private Account account;
	@ManyToOne()
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	private Timestamp tookPlace;
	private int amount;
	private int transactionType; // 0 - Deposit, 1 - Withdraw, 2 - POS, 3 - Transfer
	
	
	public Transaction(Account account, Customer customer, Timestamp tookPlace, int amount, int transactionType) {
		super();
		this.account = account;
		this.customer = customer;
		this.tookPlace = tookPlace;
		this.amount = amount;
		this.transactionType = transactionType;
	}
	
	public Transaction() {
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Timestamp getTookPlace() {
		return tookPlace;
	}


	public void setTookPlace(Timestamp tookPlace) {
		this.tookPlace = tookPlace;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getTransactionId() {
		return id;
	}


	public void setTransactionId(int id) {
		this.id = id;
	}


	public int getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}


	@Override
	public String toString() {
		return "Transaction [account=" + account + ", customer=" + customer + ", tookPlace=" + tookPlace + ", amount="
				+ amount + ", id=" + id + ", transactionType=" + transactionType + "]";
	} 
	
}
