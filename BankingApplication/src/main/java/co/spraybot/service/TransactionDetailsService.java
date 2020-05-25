package co.spraybot.service;

import java.util.List;

import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import co.spraybot.model.Account;
import co.spraybot.model.BankLocation;
import co.spraybot.model.Customer;
import co.spraybot.model.Privilege;
import co.spraybot.model.Role;

public final class TransactionDetailsService {
	static SessionFactory sesFactory;
	static Session session;
	static Transaction tx ;
	
	public static void init() {
		// TODO Auto-generated constructor stub
		sesFactory = new Configuration().configure().addAnnotatedClass(BankLocation.class).addAnnotatedClass(Customer.class).addAnnotatedClass(Account.class).addAnnotatedClass(co.spraybot.model.Transaction.class).addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class).buildSessionFactory();
		session = sesFactory.openSession();
	}
	

	public static List<Account> getAccounts(int cId){
		init();
		tx = session.beginTransaction();
		
		Query query1 = session.createQuery("FROM Account WHERE customer_id=:cId");
		query1.setParameter("cId", cId);
		List<Account> accounts = query1.list();
		return accounts;
	}
}
