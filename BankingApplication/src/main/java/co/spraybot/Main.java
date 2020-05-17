package co.spraybot;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Main {
	public static void main(String[] args) throws ParseException{
		Customer c1 = new Customer(1, "Shehan", "Atukorala", "1945 Dundas Ave, Toronto, ON", "male", new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1999-01-19").getTime()), "shehanatuk@gmail.com", "226-260-8289", "Canadian", (long) 40000);
		Customer c2 = new Customer(2, "Bob", "Smith", "1211 Aldelaide Ave, Toronto, ON", "male", new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1987-04-23").getTime()), "bobSmith@uwindsor.ca", "226-245-3421", "American", (long) 23000);
		Random ran = new Random();
		Account a1 = new Account(1, c1, ran.nextInt(1000), ran.nextInt(1000));
		Account a2 = new Account(2, c1, ran.nextInt(1000), ran.nextInt(1000));
		Account a3 = new Account(3, c1, ran.nextInt(1000), ran.nextInt(1000));
		Account a4 = new Account(4, c2, ran.nextInt(1000), ran.nextInt(1000));
		Account a5 = new Account(5, c2, ran.nextInt(1000), ran.nextInt(1000));
		Account a6 = new Account(6, c2, ran.nextInt(1000), ran.nextInt(1000));
		Configuration con = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(Account.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sesFactory = con.buildSessionFactory(registry);
		Session session = sesFactory.openSession();
		
		session.getTransaction().begin();
		session.save(c1);
		session.save(c2);
		session.save(a1);
		session.save(a2);
		session.save(a3);
		session.save(a4);
		session.save(a5);
		session.save(a6);
		
		session.getTransaction().commit();
		session.close();
		
		
	}
}
