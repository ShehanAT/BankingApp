package co.spraybot;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.spraybot.dao.CustomerRepository;
import co.spraybot.model.Account;
import co.spraybot.model.BankLocation;
import co.spraybot.model.Customer;
import co.spraybot.model.Privilege;
import co.spraybot.model.Role;
import co.spraybot.security.CustomerDetailsService;
import co.spraybot.service.CustomerService;
import co.spraybot.service.TransactionService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Main {
	public static void main(String[] args) throws Throwable{
//		Customer c1 = customers.remove(0);
//		Account a1 = new Account(1, c1, 1, 1000, 1); // accountType=1(checking)
//		Timestamp time1 = new Timestamp(System.currentTimeMillis());
//		Transaction t1 = new Transaction(1, a1, c1, time1, 500, 0); // transaction type = 0(deposit)
//		a1.setBalance(a1.getBalance() + 500);
	
		TransactionService ts = new TransactionService();
//		ts.depositFunds(1, 500);
//		ts.withdrawFunds(1, 900);
		ts.transferFunds(2, 1, 500);
		SpringApplication.run(Main.class, args);
	}
}
