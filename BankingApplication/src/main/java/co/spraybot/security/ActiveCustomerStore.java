package co.spraybot.security;

import java.util.ArrayList;
import java.util.List;

import co.spraybot.model.Customer;

public class ActiveCustomerStore {
	public List<String> customers;
	
	public ActiveCustomerStore() {
		this.customers = new ArrayList<String>();
	}
	
	public List<String> getCustomers(){
		return customers;
	}
	
	public void setCustomers(List<String> customers) {
		this.customers = customers;
	}
}
