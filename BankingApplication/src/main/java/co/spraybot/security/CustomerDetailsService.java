package co.spraybot.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.spraybot.dao.CustomerRepository;
import co.spraybot.model.Customer;
import co.spraybot.model.Privilege;
import co.spraybot.model.Role;
import co.spraybot.service.LoginAttemptService;

@Service("customerDetailsService")
@Transactional
public class CustomerDetailsService implements UserDetailsService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private LoginAttemptService loginAttemptService;
	
	@Autowired
	private HttpServletRequest request;

	public CustomerDetailsService() {
		super();
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String ip = getClientIP();
		if(loginAttemptService.isBlocked(ip)) {
			throw new RuntimeException("blocked");
		}
		
		try {
			
			Customer customer = customerRepository.findByEmail(email);
			if(customer == null) {
				throw new UsernameNotFoundException("No customer found with email: " + email);
			}
			return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), customer.isEnabled(), true, true, true, getAuthorities(customer.getRoles()));
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage()); // this counts as a return statement
		}
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles){
		return getGrantedAuthorities(getPrivileges(roles));
	}
	
	public List<GrantedAuthority> getGrantedAuthorities(List<String> privileges){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(String privilege: privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
	
	public List<String> getPrivileges(Collection<Role> roles){
		List<String> privileges = new ArrayList<String>();
		List<Privilege> collection = new ArrayList<Privilege>();
		for(Role role: roles) {
			collection.addAll(role.getPrivileges());
		}
		for(Privilege privilege: collection) {
			privileges.add(privilege.getName());
		}
		return privileges;
	}
	
	public String getClientIP() {
		String xHeader = request.getHeader("X-Forwarded-For");
		if(xHeader == null)
			return request.getRemoteAddr();
		return xHeader.split(",")[0];
		
	}

}
