package co.spraybot.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import co.spraybot.service.LoginAttemptService;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>{

	@Autowired 
	HttpServletRequest request;
	
	@Autowired
	LoginAttemptService loginAttemptService;
	
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authFail) {
		String xHeader = request.getHeader("X-Forwared-For");
		
		if(xHeader == null) {
			loginAttemptService.loginFailed(request.getRemoteAddr()); // sends string of failed auth ip address to loginFailed
		}
		else {
			loginAttemptService.loginFailed(xHeader.split(",")[0]);
		}
	}
}
