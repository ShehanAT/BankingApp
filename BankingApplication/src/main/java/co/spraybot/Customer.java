package co.spraybot;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
@Table(name="customer")
public class Customer {
	@Id
	private int customerId;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;
	private Date DOB;
	private String email;
	private String mobile;
	private String nationality;
	private long salary;
	@OneToMany(mappedBy="customer")
	private List<Account> accountIds;
	
	
	public Customer(int customerId, String firstName, String lastName, String address, String gender, Date DOB,
			String email, String mobile, String nationality, long salary) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.gender = gender;
		this.DOB = DOB;
		this.email = email;
		this.mobile = mobile;
		this.nationality = nationality;
		this.salary = salary;
		this.accountIds = new ArrayList<Account>();
	}
	
	public Customer() {
		
	}
	
	public List<Account> getAccountIds() {
		return accountIds;
	}

	public void addAccountId(Account accountId) {
		this.accountIds.add(accountId);
	}

	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date DOB) {
		DOB = DOB;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", gender=" + gender + ", DOB=" + DOB + ", email=" + email + ", mobile="
				+ mobile + ", nationality=" + nationality + ", salary=" + salary + "]";
	}
}
