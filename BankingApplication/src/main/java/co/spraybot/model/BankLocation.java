package co.spraybot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="banklocation", schema="public")
public class BankLocation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="locationname")
	private String locationName;
	@Column(name="address")
	private String address;
	@Column(name="open")
	private boolean open;
	@OneToMany(mappedBy="bankLocation")
	private List<Customer> customers;
	public BankLocation(String locationName, String address, boolean open) {
		super();
		this.locationName = locationName;
		this.address = address;
		this.open = open;
		this.customers = new ArrayList<Customer>();
	}
	public BankLocation() {
		this.customers = new ArrayList<Customer>();
	}
	public int getBankId() {
		return id;
	}
	public void setBankId(int id) {
		this.id = id;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}
	@Override
	public String toString() {
		return "BankLocation [bankId=" + id + ", locationName=" + locationName + ", address=" + address + ", open="
				+ open + ", customers=" + customers + "]";
	}
	
	
	
}
