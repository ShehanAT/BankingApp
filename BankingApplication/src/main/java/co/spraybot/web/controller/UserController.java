package co.spraybot.web.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.mapping.Map;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import co.spraybot.model.Customer;

@Controller
public class UserController {
	@RequestMapping(value="/user")
	public ModelAndView hello(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateTime = dtf.format(now);
		HashMap params = new HashMap<String, Object>();
		params.put("dateTime", dateTime);
		params.put("currentUser", currentUsername);
		return new ModelAndView("user/profile.html", params);
//		return "user/index.html";
	}
	
	@RequestMapping(value="/homepage")
	public ModelAndView homepage(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		HashMap params = new HashMap<String, Object>();
		if(authentication.getName() != "anonymousUser") {
			Customer currentUser = (Customer) authentication.getPrincipal();
			params.put("email", currentUser.getEmail());
			return new ModelAndView("/homepage.html", params);
		}
		else {
			return new ModelAndView("/home.html", params);
		}
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	}

}
