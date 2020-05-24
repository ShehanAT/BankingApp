package co.spraybot.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;

public class validEmailValidator implements ConstraintValidator<ValidEmail, String>{
	private static Pattern pattern;
	private static Matcher match;
	private static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return validateEmail(email);
	}
	
	public boolean validateEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		match = pattern.matcher(email);
		return match.matches();
	}
}
