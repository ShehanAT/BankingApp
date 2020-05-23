package co.spraybot;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.spraybot.model.Account;
import co.spraybot.model.BankLocation;
import co.spraybot.model.Customer;
import co.spraybot.model.Privilege;
import co.spraybot.model.Role;
import co.spraybot.model.Transaction;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Main {
	public static void main(String[] args) throws Throwable{
		SpringApplication.run(Main.class, args);
	}
}
