package co.spraybot.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails{
	private String verificationCode;
	private long serialVersionUID = 1L;
	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		this.verificationCode = request.getParameter("code");
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	
	
}
