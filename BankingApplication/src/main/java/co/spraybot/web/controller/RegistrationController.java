package co.spraybot.web.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import co.spraybot.dto.CustomerDTO;
import co.spraybot.error.CustomerAlreadyExistException;
import co.spraybot.model.Customer;
import co.spraybot.model.VerificationToken;
import co.spraybot.service.CustomerService;
import co.spraybot.service.ICustomerService;

@Controller
public class RegistrationController {
	@Autowired
	private CustomerService customerService;
	private MailSender mailSender;
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private MessageSource messageSource; // interfaces do not have to be instantiated
	
	public RegistrationController() {
		super();
	}
	
	@ModelAttribute("customer")
	public CustomerDTO customerDTO() {
		return new CustomerDTO();
	}
	
	@GetMapping(value="/user/registration")
	public String showRegistrationForm(WebRequest request, Model model){
		CustomerDTO newCustomer = new CustomerDTO();
		model.addAttribute("customer", newCustomer);
		return "user/registration.html";
	}
	@PostMapping(value="/user/registration") // this is the post method that handles registration form submission, @PostMapping = @RequestMapping.POST
	public ModelAndView newCustomerAccount(
		@ModelAttribute("customer") @Valid CustomerDTO customer, HttpServletRequest request, Errors errors)
	{
		try {
			Customer registered = customerService.registerNewCustomer(customer);
		}
		catch(CustomerAlreadyExistException e) {
			ModelAndView mav = new ModelAndView("user/registration.html", "customer", customer);
			mav.addObject("message", e.getMessage());
			return mav;
		}catch(Exception e) {
			ModelAndView mav = new ModelAndView("error/userError.html", "customer", customer);
			System.out.println(e.getMessage());
			mav.addObject("error", e);
			return mav;
		}
		return new ModelAndView("user/successRegister.html", "customer", customer);
	}
	
	@GetMapping("/user/resendRegistrationToken")
	public String resendRegistrationToken(final HttpServletRequest request, final Model model, @RequestParam("token") final String existingToken) {
		final Locale locale = request.getLocale();
		
		final VerificationToken newToken = customerService.generateNewVerificationToken(existingToken);
		final Customer customer = customerService.getCustomer(newToken.getToken()); // get customer based on vToken 
		try {
			//send verification token to newly registered user's email 
			final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			final SimpleMailMessage email = constructResetVerificationTokenEmail(appUrl, request.getLocale(), newToken, customer);
			mailSender.send(email);
		}
		catch(final Exception e) {
			((org.slf4j.Logger) LOGGER).debug(e.getLocalizedMessage(), e);
			model.addAttribute("message", e.getLocalizedMessage());
			return "redirect:/user/registration.html";
		}
		model.addAttribute("message", messageSource.getMessage("message.resendToken", null, locale));
		return "redirect:/login.html?lang=" + locale.getLanguage();
	}
	
	private final SimpleMailMessage constructResetVerificationTokenEmail(final String contextPath, final Locale locale, final VerificationToken newToken, final Customer customer) {
		final String confirmationUrl = contextPath + "/user/registrationConfirm.html?token=" + newToken.getToken();
		final String message = messageSource.getMessage("message.resendToken", null, locale);
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Resent Registration Token for Banking Application");
		email.setText(message + "\r\n" + confirmationUrl);
		email.setTo(customer.getEmail());
		email.setFrom("localhost@localhost.com"); // temporary email 
		return email;
	}
	
	private final SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final Customer customer) {
		final String url = contextPath + "/user/changePassword?id=" + customer.getCustomerId() + "&token=" + token;
		final String message = messageSource.getMessage("message.resetPassword", null, locale);
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(customer.getEmail());
		email.setSubject("Reset Password");
		email.setText(message + "\r\n" + url);
		email.setFrom("localhost@localhost.com"); // temporary email
		return email;
	}
}
