package co.spraybot.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

import co.spraybot.service.ICustomerService;

@Component
public class DifferentLocationChecker implements UserDetailsChecker{
	@Autowired
	public ICustomerService customerService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Override
	public void check(UserDetails userDetails) {
		String ip = getClientIP();
		// NOTE: discontinued till further notice 
	}
	
	public String getClientIP() {
		String xHeader = request.getHeader("X-Forwarded-For");
		if(xHeader == null) {
			return request.getRemoteAddr(); // return client's ip address 
		}
		return xHeader.split(",")[0];
	}
}
