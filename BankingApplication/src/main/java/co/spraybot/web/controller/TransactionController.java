package co.spraybot.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.spraybot.dto.DepositWithdrawFundDTO;
import co.spraybot.dto.TransferFundDTO;
import co.spraybot.model.Account;
import co.spraybot.model.Customer;
import co.spraybot.service.TransactionDetailsService;
import co.spraybot.service.TransactionService;

@Controller
public class TransactionController {
	@RequestMapping(value="/depositFunds")
	public ModelAndView getDepositView() {
		HashMap params = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getName() != "anonymousUser") {
			Customer customer = (Customer) authentication.getPrincipal();
			Integer cId = ((Long)customer.getCustomerId()).intValue();
			List<Account> accounts = TransactionDetailsService.getAccounts(cId);
			DepositWithdrawFundDTO depositFunds = new DepositWithdrawFundDTO();
			params.put("customerId", cId);
			params.put("accounts", accounts);
			params.put("depositFunds", depositFunds);
			return new ModelAndView("/transaction/depositFunds.html", params);
		}else {
			return new ModelAndView("/transaction/transactionError.html", params);
		}
		
		
	}
	
	@RequestMapping(value="/withdrawFunds")
	public ModelAndView getWithdrawView() {
		HashMap params = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getName() != "anonymousUser") {
			Customer customer = (Customer) authentication.getPrincipal();
			Integer cId = ((Long)customer.getCustomerId()).intValue();
			TransactionDetailsService tds = new TransactionDetailsService();
			List<Account> accounts = tds.getAccounts(cId);
			DepositWithdrawFundDTO withdrawFunds = new DepositWithdrawFundDTO();
			params.put("customerId", cId);
			params.put("accounts", accounts);
			params.put("withdrawFundsObject", withdrawFunds);
			return new ModelAndView("/transaction/withdrawFunds.html", params);
		}else {
			return new ModelAndView("/transaction/withdrawFunds.html", params);
		}
	}
	
	@RequestMapping(value="/transferFunds")
	public ModelAndView getTransferView() {
		HashMap params = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getName() != "anonymousUser") {
			Customer customer = (Customer) authentication.getPrincipal();
			Integer cId = ((Long)customer.getCustomerId()).intValue();
			List<Account> accounts = TransactionDetailsService.getAccounts(cId);
			TransferFundDTO transferFunds = new TransferFundDTO();
			params.put("transferFunds", transferFunds);
			params.put("customerId", cId);
			params.put("accounts", accounts);
			return new ModelAndView("/transaction/transferFunds.html", params);
		}
		else {
			return new ModelAndView("/transaction/transferFunds.html", params);
		}
	}
	
	@PostMapping(value="/withdrawFunds")
	public ModelAndView postWithdraw(@Valid DepositWithdrawFundDTO withdrawFunds, HttpServletRequest request, Errors errors) {
		HashMap params = new HashMap<String, Object>();
		ModelAndView mav;
		try {
			TransactionService ts = new TransactionService();
			ts.withdrawFunds(withdrawFunds.getAccountId(), withdrawFunds.getAmount());
			String succMessage = "Funds have been withdrawn successfully from your account.";
			mav = new ModelAndView("/homepage", "message", succMessage);
			return mav;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			mav = new ModelAndView("transaction/transactionError.html", "error", e.getMessage());
			mav.addObject("error", e);
			return mav;
		}
	}
	
	@PostMapping(value="/depositFunds")
	public ModelAndView postDeposit(@Valid DepositWithdrawFundDTO depositFunds, HttpServletRequest request, Errors errors) {
		HashMap params = new HashMap<String, Object>();
		ModelAndView mav;
		try {
			TransactionService ts = new TransactionService();
			ts.depositFunds(depositFunds.getAccountId(), depositFunds.getAmount());
			String succMessage = "Funds have been deposited successfully to your account.";
			mav = new ModelAndView("/homepage", "message", succMessage);
			return mav;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			mav = new ModelAndView("transaction/transactionError.html", "error", e.getMessage());
			mav.addObject("error", e);
			return mav;
		}
	}
	
	@PostMapping(value="/transferFunds")
	public ModelAndView postDeposit(@Valid TransferFundDTO transferFunds, HttpServletRequest request, Errors errors) {
		HashMap params = new HashMap<String, Object>();
		ModelAndView mav;
		try {
			TransactionService.transferFunds(transferFunds.getFirstAccId(), transferFunds.getSecondAccId(), transferFunds.getAmount());
			String succMessage = "Funds have been transfered successfully between your accounts.";
			mav = new ModelAndView("/homepage", "message", succMessage);
			return mav;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			mav = new ModelAndView("/transaction/transactionError.html", "error", e.getMessage());
			mav.addObject("error", e);
			return mav;
		}
	}
	

	

}
