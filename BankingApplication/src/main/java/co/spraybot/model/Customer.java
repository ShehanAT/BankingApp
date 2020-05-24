package co.spraybot.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="customer", schema="public")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customerid")
	private long customerId;
	@Column(name="firstname")
	private String firstName;
	@Column(name="lastname")
	private String lastName;
	private String address;
	private String gender;
	@Column(name="dob")
	private Date DOB;
	private String email;
	private String mobile;
	private String nationality;
	@Column(length = 60)
	private String password;
	private int salary;
	
	private boolean enabled;
	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(mappedBy="customer")
	private List<Account> accountIds;
	@ManyToOne()
	@JoinColumn(name="bank_location_id", nullable=true)
	private BankLocation bankLocation;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="customer_roles", joinColumns = @JoinColumn(name="customer_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


	public Customer(long customerId, String firstName, String lastName, String address, String gender,
			String email, String mobile, String nationality, int salary) {
		super();
		this.customerId =  customerId;
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
		this.DOB = new Date(System.currentTimeMillis());
	}
	
	

	public Customer() {
		
	}
	
	public List<Account> getAccountIds() {
		return accountIds;
	}

	public void addAccountId(Account accountId) {
		this.accountIds.add(accountId);
	}

	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		this.DOB = DOB;
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public BankLocation getBankLocation() {
		return bankLocation;
	}

	public void setBankLocation(BankLocation bankLocation) {
		this.bankLocation = bankLocation;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", gender=" + gender + ", DOB=" + DOB + ", email=" + email + ", mobile="
				+ mobile + ", nationality=" + nationality + ", salary=" + salary + "]";
	}
	
}
