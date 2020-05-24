package co.spraybot.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import co.spraybot.model.Account;
import co.spraybot.model.BankLocation;
import co.spraybot.model.Customer;
import co.spraybot.model.Privilege;
import co.spraybot.model.Role;
import org.hibernate.Transaction;

public final class TransactionService {
	static SessionFactory sesFactory;
	static Session session;
	static Transaction tx ;
	
	public static void init() {
		// TODO Auto-generated constructor stub
		sesFactory = new Configuration().configure().addAnnotatedClass(BankLocation.class).addAnnotatedClass(Customer.class).addAnnotatedClass(Account.class).addAnnotatedClass(co.spraybot.model.Transaction.class).addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class).buildSessionFactory();
		session = sesFactory.openSession();
	}
	
	public static void depositFunds(int aId, int depositAmount) {
		init();
		tx = session.beginTransaction();
		Query query1 = session.createQuery("UPDATE Account SET balance=balance+:depositAmount WHERE accountId=:aId");
		query1.setParameter("depositAmount", depositAmount);
		query1.setParameter("aId", aId);
		int queryStatus = query1.executeUpdate();

		tx.commit();
		session.close();
	}
	
	public static void withdrawFunds(int aId, int withdrawAmount) {
		/*Tasks:
		 * 1. update Account.balance = Account.balance - withdrawAmount
		 * 2. save Account.customerId in local var
		 * 3. save customer and account objects to local vars
		 * 2. insert Transaction record into Transaction with customerId and aId
		 */
		init();
		tx = session.beginTransaction();
		Query query1 = session.createQuery("UPDATE Account SET balance=balance-:withdrawAmount WHERE id=:aId");
		query1.setParameter("aId", aId);
		query1.setParameter("withdrawAmount", withdrawAmount);
		query1.executeUpdate();
	
		
		query1 = session.createQuery("FROM Account WHERE id=:aId");
		query1.setParameter("aId", aId);
		Account a1 = (Account) query1.list().remove(0);
		
		query1 = session.createSQLQuery("SELECT customer_id FROM public.account WHERE id=" + aId + ";");
		List result1 = query1.list();
		Integer cId = ((BigInteger)result1.remove(0)).intValue();
		
		query1 = session.createQuery("FROM Customer AS c WHERE cast(c.customerId as int)=:cId");
		query1.setParameter("cId", cId);
		Customer c1 = (Customer) query1.list().remove(0);
		
		co.spraybot.model.Transaction t1 = new co.spraybot.model.Transaction(a1, c1, new Timestamp(System.currentTimeMillis()), withdrawAmount, 1); // last param=transaction type: withdraw
		session.save(t1);
		
		tx.commit();
		session.close();
	}
}
