package co.spraybot.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.spraybot.dto.AccountDTO;
import co.spraybot.model.Account;
import co.spraybot.model.Customer;
import co.spraybot.service.AccountService;
import co.spraybot.service.TransactionDetailsService;

@Controller
public class AccountController {
	@RequestMapping("/addAccount")
	public ModelAndView getAddAccountView() {
		HashMap params = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getName() != "anonymousUser") {
			Customer customer = (Customer) authentication.getPrincipal();
			Integer cId = ((Long)customer.getCustomerId()).intValue();
			AccountDTO accountDTO = new AccountDTO();
			params.put("customerId", cId);
			params.put("account", accountDTO);
			return new ModelAndView("/account/addaccount.html", params);
		}else {
			return new ModelAndView("/account/accountError.html", params);
		}
	}
	
	@RequestMapping("/deleteAccount")
	public ModelAndView getDeleteAccountView() {
		HashMap params = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getName() != "anonymousUser") {
			Customer customer = (Customer) authentication.getPrincipal();
			Integer cId = ((Long)customer.getCustomerId()).intValue();
			List<Account> accounts = TransactionDetailsService.getAccounts(cId);
			params.put("customerId", cId);
			params.put("accounts", accounts);
			return new ModelAndView("/account/deleteaccount.html", params);
		}else {
			return new ModelAndView("/account/deleteAccount.html", params);
		}
	}
	
	@PostMapping(value="/addAccount")
	public ModelAndView postAddAccount(@Valid AccountDTO accountDTO) {
		HashMap params = new HashMap<String, Object>();
		try {
			AccountService.addAccount(accountDTO);
			String message = "New account successfully created!";
			params.put("message", message);
			return new ModelAndView("/homepage.html", params);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			params.put("error", e.getMessage());
			return new ModelAndView("/account/accountError.html", params);
		}
	}
	
	@PostMapping(value="/deleteAccount")
	public ModelAndView postDeleteAccountView(HttpServletRequest request,
            HttpServletResponse response) {
		HashMap params = new HashMap<String, Object>();
		int aId = Integer.parseInt(request.getParameter("accountSelectId"));
		try {
			AccountService.deleteAccount(aId);
			String message = "The selected account has been successfully deleted!";
			params.put("message", message);
			return new ModelAndView("/homepage.html", params);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			params.put("error", e.getMessage());
			return new ModelAndView("/account/accountError.html", params);
		}
	}
	
}
