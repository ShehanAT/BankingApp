package co.spraybot.service;

import java.util.Optional;

import co.spraybot.dto.CustomerDTO;
import co.spraybot.error.CustomerAlreadyExistException;
import co.spraybot.model.Customer;
import co.spraybot.model.VerificationToken;

public interface ICustomerService {
	Customer getCustomer(final String verificationToken);
	Customer registerNewCustomer(CustomerDTO customerDTO) throws CustomerAlreadyExistException;
	VerificationToken generateNewVerificationToken(final String existingVerificationToken);
	void createVerificationTokenForCustomer(final Customer customer, final String token);
	String validateVerificationToken(String token);
	Customer findCustomerByEmail(String email);
	Optional<Customer> getCustomerById(long id);
	
}
