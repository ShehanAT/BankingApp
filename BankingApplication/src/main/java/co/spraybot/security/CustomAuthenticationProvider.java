package co.spraybot.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import co.spraybot.dao.CustomerRepository;
import co.spraybot.model.Customer;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider{
	@Autowired 
	private CustomerRepository customerRepository;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException{
		Customer customer = customerRepository.findByEmail(auth.getName());
		
		if(customer == null) {
			throw new BadCredentialsException("Invalid email or password");
		}
		
		Authentication result = super.authenticate(auth);
		return new UsernamePasswordAuthenticationToken(customer, result.getCredentials(), result.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
