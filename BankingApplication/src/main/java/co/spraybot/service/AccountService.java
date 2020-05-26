package co.spraybot.service;


import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import co.spraybot.dto.AccountDTO;
import co.spraybot.model.Account;
import co.spraybot.model.BankLocation;
import co.spraybot.model.Customer;
import co.spraybot.model.Privilege;
import co.spraybot.model.Role;

public final class AccountService {
	static SessionFactory sesFactory;
	static Session session;
	static Transaction tx;
	
	public static void init() {
		sesFactory = new Configuration().configure().addAnnotatedClass(BankLocation.class).addAnnotatedClass(Customer.class).addAnnotatedClass(Account.class).addAnnotatedClass(co.spraybot.model.Transaction.class).addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class).buildSessionFactory();
		session = sesFactory.openSession();
	}
	
	public static void addAccount(AccountDTO accountDTO) {
		init();
		tx = session.beginTransaction();
		
		Account newAccount = new Account();
		//save customer object to local var
	
		Query query1 = session.createQuery("FROM Customer AS c WHERE cast(c.customerId as int)=:cId");
		query1.setParameter("cId", accountDTO.getCustomerId());
		
		Customer c1 = (Customer) query1.list().remove(0);
		newAccount.setCustomer(c1);
		newAccount.setAccountNum(accountDTO.getAccountNumber());
		newAccount.setAccountType(accountDTO.getAccountType());
		newAccount.setBalance(accountDTO.getBalance());

		session.save(newAccount);
		
		tx.commit();
		session.close();
	}
	
	public static void deleteAccount(int aId) {
		init();
		tx = session.beginTransaction();
		
		Query query1 = session.createSQLQuery("DELETE FROM public.Transaction WHERE account_id=" + aId + ";");
		query1.executeUpdate();
		
		query1 = session.createQuery("DELETE Account WHERE id=:aId");
		query1.setParameter("aId", aId);
		query1.executeUpdate();
		
		tx.commit();
		session.close();
	}
}
