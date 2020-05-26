package co.spraybot.web.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.spraybot.dto.AccountDTO;
import co.spraybot.dto.BankLocationDTO;
import co.spraybot.model.Customer;
import co.spraybot.service.SettingsService;

@Controller
public class SettingsController {
	
	
	@RequestMapping(value="/setBankLocation")
	public ModelAndView getSetBankLocationView() {
		HashMap params = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getName() != "anonymousUser") {
			Customer customer = (Customer) authentication.getPrincipal();
			Integer cId = ((Long)customer.getCustomerId()).intValue();
			BankLocationDTO bankLocation = new BankLocationDTO();
			params.put("customerId", cId);
			params.put("bankLocation", bankLocation);
			return new ModelAndView("/settings/setBankLocation.html", params);
		}else {
			return new ModelAndView("/settings/settingsError.html", params);
		}
	}
	
	@PostMapping(value="/setBankLocation")
	public ModelAndView postSetBankLocation(@Valid BankLocationDTO bankLocationDTO) {
		HashMap params = new HashMap<String, Object>();
		try {
			SettingsService.setBankLocation(bankLocationDTO);
			String message = "Your default bank location has been set succesfully!";
			params.put("message", message);
			return new ModelAndView("/homepage.html", params);
		}catch(Exception e) {
			String error = e.getMessage();
			System.out.println(error);
			params.put("error", error);
			return new ModelAndView("/settings/settingsError.html", params);
		}
	}
}
