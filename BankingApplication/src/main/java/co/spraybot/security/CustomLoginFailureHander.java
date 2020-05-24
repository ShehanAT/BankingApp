package co.spraybot.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component("authenticationFailureHandler")
public class CustomLoginFailureHander extends SimpleUrlAuthenticationFailureHandler {
	//  SimpleUrlAuthenticationFailureHandle is a class 
	@Autowired
	public MessageSource messageSource;
	
	@Autowired
	public LocaleResolver localeResolver; // interface
	
	@Override 
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException{
		super.onAuthenticationFailure(request, response, exception);
		
		setDefaultFailureUrl("/login?error=true");
		
		Locale locale = localeResolver.resolveLocale(request);
		
		String errorMessage = messageSource.getMessage("message.badCredentials", null, locale); 
		
		if(exception.getMessage().equalsIgnoreCase("User account has expired")) {
			errorMessage = messageSource.getMessage("auth.message.expired", null, locale);
		}
		else if(exception.getMessage().equalsIgnoreCase("User is disabled")) {
			errorMessage = messageSource.getMessage("auth.message.disabled", null, locale);
		}
		else if(exception.getMessage().equalsIgnoreCase("blocked")) {
			errorMessage = messageSource.getMessage("auth.message.blocked", null, locale);
		}
		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
	
		
	}
}
