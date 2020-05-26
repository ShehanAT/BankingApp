package co.spraybot.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import co.spraybot.dto.BankLocationDTO;
import co.spraybot.model.Account;
import co.spraybot.model.BankLocation;
import co.spraybot.model.Customer;
import co.spraybot.model.Privilege;
import co.spraybot.model.Role;

public final class SettingsService {
	static SessionFactory sesFactory;
	static Session session;
	static Transaction tx;
	
	public static void init() {
		sesFactory = new Configuration().configure().addAnnotatedClass(BankLocation.class).addAnnotatedClass(Customer.class).addAnnotatedClass(Account.class).addAnnotatedClass(co.spraybot.model.Transaction.class).addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class).buildSessionFactory();
		session = sesFactory.openSession();
	}
	
	public static void setBankLocation(BankLocationDTO bankLocation) {
		/*Tasks:
		 * 1. create and save new BankLocation obj
		 * 2. save corresponding Customer obj to local var and set bankLocation attribute to new BankLocation obj
		 */
		init();
		tx = session.beginTransaction();
		
		Query query1 = session.createQuery("FROM Customer AS c WHERE cast(c.customerId as int)=:cId");
		query1.setParameter("cId", bankLocation.getCustomerId());
		Customer customer = (Customer) query1.list().remove(0);
		
		
		BankLocation bank1 = new BankLocation();
		bank1.setAddress(bankLocation.getAddress());
		bank1.setLocationName(bankLocation.getLocationName());
		bank1.setOpen(bankLocation.isEnabled());
		bank1.addCustomer(customer);
		customer.setBankLocation(bank1);
		
		
		session.save(bank1);
		session.update(customer);
		
		tx.commit();
		session.close();
		
	}
}
