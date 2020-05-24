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
	@Column(name="fromAccId", nullable=true)
	private int fromAccId;
	@Column(name="toAccId", nullable=true)
	private int toAccId;
	
	public Transaction(Account account, Customer customer, Timestamp tookPlace, int amount, int transactionType) {
		super();
		this.account = account;
		this.customer = customer;
		this.tookPlace = tookPlace;
		this.amount = amount;
		this.transactionType = transactionType;
	}
	
	public Transaction(Account account, int secondAccountId, Customer customer, Timestamp tookPlace, int amount) {
		// this constructor used only for transactionType 3 - Transfer
		super();
		this.account = account;
		this.customer = customer;
		this.tookPlace = tookPlace;
		this.amount = amount;
		this.fromAccId = account.getAccountId();
		this.toAccId = secondAccountId;
		this.transactionType = 3;
	}
	
	
	public Transaction() {
	}


	public Account getAccount() {
		return account;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromAccId() {
		return fromAccId;
	}

	public void setFromAccId(int fromAccId) {
		this.fromAccId = fromAccId;
	}

	public int getToAccId() {
		return toAccId;
	}

	public void setToAccId(int toAccId) {
		this.toAccId = toAccId;
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
