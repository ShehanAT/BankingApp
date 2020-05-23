package co.spraybot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.spraybot.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	// interface extending an interface
	@Override 
	void delete(Customer customer);
	
	Customer findByEmail(String email);
}
