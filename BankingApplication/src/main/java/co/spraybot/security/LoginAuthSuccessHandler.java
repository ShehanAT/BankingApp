package co.spraybot.security;


import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import co.spraybot.model.Customer;

@Component("myAuthenticationSuccessHandler")
public class LoginAuthSuccessHandler implements AuthenticationSuccessHandler{
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public ActiveCustomerStore activeCustomerStore;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Bean
	public ActiveCustomerStore activeCustomerStore() {
		return new ActiveCustomerStore();
	}
	
//	@Bean 
//	public UserDetailsService userDetailsService() {
//		return new UserDetailsService();
//	}
	
	@Override 
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
		handle(request, response, authentication);
		HttpSession session = request.getSession();
		if(session != null) {  // set current customer session obj to authed customer
			session.setMaxInactiveInterval(30 * 60);
			
			String email;
			if(authentication.getPrincipal() instanceof Customer) {
				email = ((Customer)authentication.getPrincipal()).getEmail();
			}else {
				email = authentication.getName(); 
			}
			LoggedCustomer customer = new LoggedCustomer(email, activeCustomerStore);
			session.setAttribute("customer", customer);
		}
		clearAuthenticationAttributes(request); // erase failed auths stored in session
		
		
	}
	
	public void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session == null) {
			return ;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
		String targetUrl = determineTargetUrl(authentication); //sets target url 
		
		if(response.isCommitted()) {
			logger.debug("Response is already commited, Unable to redirect to " + targetUrl);
			return ;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl); // redirects authed customer to homepage or console page 
	}
	
	public String determineTargetUrl(Authentication authentication) {
		// determines whether authed customer is reg user or admin
		boolean isUser = false;
		boolean isAdmin = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority authority: authorities) {
			if(authority.getAuthority().equals("READ_PRIVLEGE")) {
				isUser = true;
			}
			else if(authority.getAuthority().equals("WRITE_PRIVLEGE")) {
				isUser = false;
				isAdmin = true;
				break;
			}
		}
		if(isUser) {
			String email;
			if(authentication.getPrincipal() instanceof Customer) {
				email = ((Customer)authentication.getPrincipal()).getEmail();
			}else {
				email = authentication.getName(); 
			}
			
			return "/homepage.html?customer=" + email;
		}
		else if(isAdmin){
			return "/console.html";
		}
		else {
			throw new IllegalStateException();
		}	
		
		
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	
	
}
