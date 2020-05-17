package co.spraybot;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

public class BankLocation {
	private int bankId;
	private String locationName;
	private String address;
	private boolean open;
	@OneToMany(mappedBy="bankLocation")
	private List<Customer> customers;
	public BankLocation(int bankId, String locationName, String address, boolean open) {
		super();
		this.bankId = bankId;
		this.locationName = locationName;
		this.address = address;
		this.open = open;
		this.customers = new ArrayList<Customer>();
	}
	public BankLocation() {
		
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
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
		return "BankLocation [bankId=" + bankId + ", locationName=" + locationName + ", address=" + address + ", open="
				+ open + ", customers=" + customers + "]";
	}
	
	
	
}
