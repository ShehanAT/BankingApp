package co.spraybot.model;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class VerificationToken {
	private static final int EXPIRATION = 60 * 24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String token;
	
	@OneToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
//	@JoinColumn(nullable = false, name = "customer_id", foreignKey = @ForeignKey(name = "FK_VERIFY_CUSTOMER"))
	private Customer customer;
	
	private Date expiryDate;
	
		public VerificationToken() {
		super();
	}
	
	public VerificationToken(final String token) {
		super();
		
		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}
	
	public VerificationToken(final String token, final Customer customer) {
		super();
		
		this.token = token;
		this.customer = customer;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public static int getExpiration() {
		return EXPIRATION;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(final String token) {
		this.token = token;
	}
	
	private Date calculateExpiryDate(final int expiryTimeInMinutes) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date(0).getTime()); // new Date(0) is new Date() in the schema, FIND A FIX
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}
	
	public void updateToken(final String token) {
		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}
	
	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		return result;
	}
}
