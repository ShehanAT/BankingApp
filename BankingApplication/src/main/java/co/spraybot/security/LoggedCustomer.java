package co.spraybot.security;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoggedCustomer implements HttpSessionBindingListener{
	private String email;
	private ActiveCustomerStore activeCustomerStore;
	
	public LoggedCustomer(String email, ActiveCustomerStore activeCustomerStore) {
		this.email = email;
		this.activeCustomerStore = activeCustomerStore;
	}
	
	@Override 
	public void valueBound(HttpSessionBindingEvent listener) {
		List<String> customers = activeCustomerStore.getCustomers();
		LoggedCustomer customer = (LoggedCustomer) listener.getValue();
		if(!customers.contains(customer.getEmail()))
			customers.add(customer.getEmail());
	}
	
	@Override 
	public void valueUnbound(HttpSessionBindingEvent listener) {
		List<String> customers = activeCustomerStore.getCustomers();
		LoggedCustomer customer = (LoggedCustomer) listener.getValue();
		if(customers.contains(customer.getEmail()))
			customers.remove(customer.getEmail());
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
