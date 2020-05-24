package co.spraybot.service;

import java.awt.print.Pageable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.spraybot.dao.CustomerRepository;
import co.spraybot.dao.RoleRepository;
import co.spraybot.dao.VerificationTokenRepository;
import co.spraybot.dto.CustomerDTO;
import co.spraybot.model.Customer;
import co.spraybot.model.VerificationToken;

@Service
@Transactional
public class CustomerService implements ICustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private VerificationTokenRepository tokenRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	public static final String TOKEN_INVALID = "invalidToken";
	public static final String TOKEN_EXPIRED = "expired";
	public static final String TOKEN_VALID = "valid";
	@Bean
	PasswordEncoder getEncoder() {
	    return new BCryptPasswordEncoder();
	}
	@Autowired // interfaces can be autowired which automatically instanstiates them 
	public PasswordEncoder passwordEncoder;
	
	@Override
	public void createVerificationTokenForCustomer(final Customer customer, final String token) {
		final VerificationToken newToken = new VerificationToken(token, customer);
		tokenRepository.save(newToken);
	}
	
	@Override
	public Customer findCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
	
	@Override
	public Customer registerNewCustomer(final CustomerDTO customerDTO) {
		final Customer customer = new Customer();
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setAddress(customerDTO.getAddress());
		customer.setEmail(customerDTO.getEmail());
		customer.setGender(customerDTO.getGender());
		customer.setNationality(customerDTO.getNationality());
		customer.setMobile(customerDTO.getMobile());
		customer.setSalary(customerDTO.getSalary());
		customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
		customer.setEnabled(true);
		customer.setRoles(Arrays.asList(roleRepository.findByName("ROLE_CUSTOMER")));
		try {
			customer.setDOB(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(customerDTO.getDOB()).getTime()));
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}
		customer.setBankLocation(null);
		
		return customerRepository.save(customer);
	}
	
	@Override
	public Optional<Customer> getCustomerById(long id){
		return customerRepository.findById(id);
	}
	
	@Override 
	public Customer getCustomer(final String verificationToken) {
		final VerificationToken token = tokenRepository.findByToken(verificationToken);
		if(token != null) {
			return token.getCustomer();
		}
		return null;
	}
	
	@Override
	public String validateVerificationToken(String token) {
		final VerificationToken verificationToken = tokenRepository.findByToken(token);
		if(verificationToken == null) // verification token is null 
			return TOKEN_INVALID;
		final Customer customer = verificationToken.getCustomer();
		final Calendar cal = Calendar.getInstance();
		if((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) { // verification token has expired
			tokenRepository.delete(verificationToken);
			return TOKEN_EXPIRED;
		}
		customer.setEnabled(true);
		customerRepository.save(customer);
		return TOKEN_VALID; // token is valid, save new customer, registration successful
	}
	
	@Override 
	public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
		VerificationToken token = tokenRepository.findByToken(existingVerificationToken);
		token.updateToken(UUID.randomUUID().toString()); // generates random number
		token = tokenRepository.save(token); //replace old token with new one
		return token;
	}
}
