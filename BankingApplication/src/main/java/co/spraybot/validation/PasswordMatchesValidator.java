package co.spraybot.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.QwertySequenceRule;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;

import com.google.common.base.Joiner;

import co.spraybot.dto.CustomerDTO;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>{
	// Object will be the customerDTO instance
	@Override
	public boolean isValid(Object customer, ConstraintValidatorContext context){
		CustomerDTO customerDTO =  (CustomerDTO) customer;
		return customerDTO.getPassword().equals(customerDTO.getMatchingPassword());
	}
}
